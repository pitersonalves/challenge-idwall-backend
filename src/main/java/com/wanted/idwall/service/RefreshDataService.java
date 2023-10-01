package com.wanted.idwall.service;

import static java.util.Objects.nonNull;

import com.wanted.idwall.enums.ClassificationEnum;
import com.wanted.idwall.mapper.PersonWantedMapper;
import com.wanted.idwall.model.WantedClassification;
import com.wanted.idwall.rest.FBIClient;
import com.wanted.idwall.rest.InterpolClient;
import com.wanted.idwall.rest.InterpolPersonClient;
import com.wanted.idwall.rest.InterpolPersonImageClient;
import com.wanted.idwall.rest.response.interpol.InterpolPersonResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshDataService {

    private static final int ITEMS_PER_PAGE = 20;
    private static final  String FBI = "FBI";
    private static final  String INTERPOL = "INTERPOL";
    private final FBIClient fbiClient;

    private final InterpolClient interpolClient;

    private final InterpolPersonClient interpolPersonClient;

    private final InterpolPersonImageClient interpolPersonImageClient;

    private final PersonWantedService personWantedService;

    private final ClassificationService classificationService;

    private final PersonWantedMapper personWantedMapper;

    @Scheduled(fixedRate = 200000)
    public void refresh() {

        refreshDataOfFBI();

        refreshDataInterpool();

        log.info("Finish refresh data");
    }

    private void refreshDataOfFBI() {
        /* Method for fetching on FBI and storing data in database */

        /* Search for the first page to evaluate the numer of pages to be scrolled through */
        var response = fbiClient.fbiWantedPersonResponseMono("1");
        log.info("response: {}", response.block().toString());

        /* Calculating the total number of pages */
        var totalPages = Math.ceil(response.block().total() / ITEMS_PER_PAGE);

        for (int i = response.block().page(); i <= totalPages + 1; i++) {
            log.info("Search page: {}", i);

            /* Search by page */
            var res = fbiClient.fbiWantedPersonResponseMono(String.valueOf(i));

            res.block().items().forEach(item -> {

                /* Validate that the person is in the database  */
                var domain = personWantedService.findByBaseId(item.uid());
                if (domain != null) {
                    log.info("Person already exists FBI: {}", domain.name());
                    return;
                }

                var newPersonWanted = personWantedMapper.toDomainFromFbiService(item, FBI);

                /* Save person on database  */
                var personSaved = personWantedService.create(newPersonWanted);
                log.info("Save New Person FBI: {}", personSaved.id());

                /* Save wanted classification  */
                classificationService.create(WantedClassification.builder()
                        .classificationName(item.subjects().size() > 0 ? item.subjects().get(0) : "NONE")
                        .personWantedId(personSaved.id())
                        .build());

            });

        }

        log.info("Finish refresh data FBI");
    }

    private void refreshDataInterpool() {

        /* Method for fetching on interpool and storing data in database */

        /* Fetching all wanted person */
        var response = interpolClient.getPeopleWanted();

        response.block()._embedded().notices().forEach(item -> {

            /* Validate that the person is in the database  */
            var domain = personWantedService.findByBaseId(item.entity_id());
            if (domain != null) {
                log.info("Person already exists Interpol: {}", domain.name());
                return;
            }

            /* Search for complete information about a person  */
            var personInfoFull = interpolPersonClient.getPersonInformationWantedFull(item.entity_id());

            /* Search images of the person  */
            var personImages = interpolPersonImageClient.getImageOfPersonInformationWantedFull(item.entity_id());

            var newPersonWanted = personWantedMapper.toDomainFromInterpolService(item, personImages.block(),
                    personInfoFull.block(), INTERPOL);

            /* Save person on database  */
            var personSaved = personWantedService.create(newPersonWanted);
            log.info("Save New Person Interpol: {}", personSaved.id());

            /* Save wanted classification  */
            classificationService.create(WantedClassification.builder()
                    .classificationName(transformClassification(personInfoFull.block().arrest_warrants()))
                    .personWantedId(personSaved.id())
                    .build());
        });

        log.info("Finish refresh data Interpol");
    }

    private String transformClassification(List<InterpolPersonResponse.ArrestWarrant> arrestWarrants) {
        if (nonNull(arrestWarrants) && arrestWarrants.size() > 0) {

            for (ClassificationEnum classification : ClassificationEnum.values()) {
                if (arrestWarrants.get(0).charge().toUpperCase().contains(classification.getName())) {
                    return classification.getCode();
                }
            }
        }
        return "NONE";
    }

}

package com.wanted.idwall.service;

import com.wanted.idwall.dto.DataViewDTO;
import com.wanted.idwall.dto.PersonWantedDTO;
import com.wanted.idwall.exceptions.ApiException;
import com.wanted.idwall.mapper.PersonWantedMapper;
import com.wanted.idwall.model.PersonWantedFilter;
import com.wanted.idwall.model.WantedClassification;
import com.wanted.idwall.repository.PersonWantedRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonWantedService {

    private final PersonWantedRepository personWantedRepository;

    private final PersonWantedMapper personWantedMapper;

    private final ClassificationService classificationService;

    public List<PersonWantedFilter> findAll(String name, String nationality, String gender, String base,
                                            String classification, Pageable pageable) {
        return personWantedRepository.findPersonWantedByFilters(name, nationality,
                gender, base, classification, pageable);
    }

    public PersonWantedDTO getById(Integer id) {
        var domain = personWantedRepository.findById(id);
        if (domain.isPresent()) {
            return personWantedMapper.toDTO(domain.get());
        }
        throw new ApiException("404", "Person Wanted not found", HttpStatus.NOT_FOUND);
    }

    public PersonWantedDTO create(PersonWantedDTO request) {
        return savePerson(request);
    }

    public PersonWantedDTO update(PersonWantedDTO request) {
        return savePerson(request);
    }

    private PersonWantedDTO savePerson(PersonWantedDTO request) {
        var domain = personWantedRepository.save(personWantedMapper.toDomain(request));
        classificationService.create(WantedClassification.builder()
                .classificationName(request.classificationName())
                .personWantedId(domain.id())
                .build());
        return personWantedMapper.toDTO(domain);
    }

    public void delete(Integer id) {
        personWantedRepository.deleteById(id);
    }

    public PersonWantedDTO findByBaseId(String baseId) {
        return personWantedMapper.toDTO(personWantedRepository.findByBaseId(baseId));
    }

    public DataViewDTO findAllDataForView() {
        return DataViewDTO.builder()
                .nationality(personWantedRepository.findAllRegisterNationality())
                .classificationName(classificationService.findAllClassificationName())
                .build();
    }
}

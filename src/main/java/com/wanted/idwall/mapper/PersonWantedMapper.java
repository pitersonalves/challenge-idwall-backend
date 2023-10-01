package com.wanted.idwall.mapper;

import com.wanted.idwall.dto.PersonWantedDTO;
import com.wanted.idwall.enums.Country;
import com.wanted.idwall.enums.Gender;
import com.wanted.idwall.model.PersonWanted;
import com.wanted.idwall.rest.response.fbi.FBIWantedPersonResponse;
import com.wanted.idwall.rest.response.interpol.InterpolPersonImage;
import com.wanted.idwall.rest.response.interpol.InterpolPersonResponse;
import com.wanted.idwall.rest.response.interpol.InterpolResponse;
import java.util.Objects;
import java.util.stream.Collectors;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {Objects.class, Gender.class, Country.class, Collectors.class}, builder =
@Builder(disableBuilder = true))
public interface PersonWantedMapper {

    PersonWanted toDomain(PersonWantedDTO personWantedDTO);

    PersonWantedDTO toDTO(PersonWanted personWantedDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "respFBI.title")
    @Mapping(target = "image", expression = "java(respFBI.images().get(0).thumb())")
    @Mapping(target = "description", source = "respFBI.description")
    @Mapping(target = "caution", source = "respFBI.caution")
    @Mapping(target = "remarks", source = "respFBI.remarks")
    @Mapping(target = "scarsMarks", source = "respFBI.scarsAndMarks")
    @Mapping(target = "placeBirth", source = "respFBI.placeOfBirth")
    @Mapping(target = "nationality", source = "respFBI.nationality")
    @Mapping(target = "gender", source = "respFBI.sex")
    @Mapping(target = "height", source = "respFBI.heightMax")
    @Mapping(target = "weight", source = "respFBI.weightMax")
    @Mapping(target = "eyes", source = "respFBI.eyes")
    @Mapping(target = "race", source = "respFBI.race")
    @Mapping(target = "base", source = "base")
    @Mapping(target = "baseId", source = "respFBI.uid")
    PersonWantedDTO toDomainFromFbiService(FBIWantedPersonResponse respFBI, String base);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", expression = "java(respInterpool.name() + \" \" + respInterpool.forename())")
    @Mapping(target = "image", expression = "java(Objects.nonNull(image._embedded().images()) && image._embedded().images().size() > 0 ? image._embedded().images().get(0)._links().self().href() : null)")
    @Mapping(target = "description", expression = "java(personInfoFull.arrest_warrants().stream().map(InterpolPersonResponse.ArrestWarrant::charge).collect(Collectors.joining(\",\")))")
    @Mapping(target = "caution", ignore = true)
    @Mapping(target = "remarks", ignore = true)
    @Mapping(target = "scarsMarks", ignore = true)
    @Mapping(target = "placeBirth", expression = "java(personInfoFull.place_of_birth())")
    @Mapping(target = "nationality", expression = "java(Objects.nonNull(personInfoFull.nationalities()) && personInfoFull.nationalities().size() > 0 ? String.valueOf(Country.getPaisByCodigo(personInfoFull.nationalities().get(0))) : null)")
    @Mapping(target = "gender", expression = "java(Gender.valueOf(personInfoFull.sex_id()).getGender())")
    @Mapping(target = "height", expression = "java(String.valueOf(personInfoFull.height()))")
    @Mapping(target = "weight", expression = "java(String.valueOf(personInfoFull.weight()))")
    @Mapping(target = "eyes", expression = "java(Objects.nonNull(personInfoFull.eyes_colors_id()) ? personInfoFull.eyes_colors_id().toString() : null)")
    @Mapping(target = "race", ignore = true)
    @Mapping(target = "base", source = "base")
    @Mapping(target = "baseId", source = "respInterpool.entity_id")
    PersonWantedDTO toDomainFromInterpolService(InterpolResponse.Notice respInterpool, InterpolPersonImage image,
                                                InterpolPersonResponse personInfoFull, String base);

}

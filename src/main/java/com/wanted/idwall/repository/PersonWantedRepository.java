package com.wanted.idwall.repository;

import com.wanted.idwall.model.PersonWanted;
import com.wanted.idwall.model.PersonWantedFilter;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonWantedRepository extends ListCrudRepository<PersonWanted, Integer>,
        ListPagingAndSortingRepository<PersonWanted, Integer> {

    PersonWanted findByBaseId(String baseId);

    @Query("SELECT DISTINCT p.nationality FROM PERSON_WANTED p")
    List<String> findAllRegisterNationality();

    @Query("SELECT DISTINCT * FROM PERSON_WANTED p "
            + "JOIN WANTED_CLASSIFICATION wc ON wc.person_wanted_id = p.id "
            + "WHERE (:name IS NULL OR p.name LIKE '%' || :name || '%') "
            + "AND (:nationality IS NULL OR p.nationality = :nationality) "
            + "AND (:gender IS NULL OR p.gender = :gender) "
            + "AND (:base IS NULL OR p.base = :base) "
            + "AND (:classificationName IS NULL OR wc.classification_name = :classificationName) "
            + "ORDER BY p.base_id DESC")
    List<PersonWantedFilter> findPersonWantedByFilters(@Param("name") String name,
                                                        @Param("nationality") String nationality,
                                                        @Param("gender") String gender, @Param("base") String base,
                                                        @Param("classificationName") String classificationName,
                                                        Pageable pageable);
}


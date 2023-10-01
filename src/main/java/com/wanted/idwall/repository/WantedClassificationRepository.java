package com.wanted.idwall.repository;

import com.wanted.idwall.model.WantedClassification;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WantedClassificationRepository extends ListCrudRepository<WantedClassification, Integer>,
        ListPagingAndSortingRepository<WantedClassification, Integer> {


    @Query("SELECT DISTINCT c.classification_name FROM WANTED_CLASSIFICATION c")
    List<String> findAllClassificationName();

    @Query("SELECT DISTINCT * FROM WANTED_CLASSIFICATION c "
            + "WHERE c.person_wanted_id = :id")
    List<WantedClassification> findByPersonWantedId(@Param("id") Integer id);
}

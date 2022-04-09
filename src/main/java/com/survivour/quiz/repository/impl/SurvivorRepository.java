package com.survivour.quiz.repository.impl;



import com.survivour.quiz.model.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurvivorRepository extends CrudRepository<Survivor,Long> {
    @Query(value = "select  * from survivor  where  is_infected=1 ", nativeQuery = true)
    List<Survivor> getListOfInfectedSurvivour();

    @Query(value = "select  * from survivor  where  is_infected=0 ", nativeQuery = true)
    List<Survivor> getListOfNonInfectedSurvivour();

    @Query(value = " SELECT COUNT(*) FROM survivor ", nativeQuery = true)
     long countSurviour();

    @Query(value = " SELECT COUNT(*) FROM survivor  where is_infected=1 ", nativeQuery = true)
    long countInfected();

    @Query(value = " SELECT COUNT(*) FROM survivor  where is_infected=0 ", nativeQuery = true)
    long countNonInfected();

    Survivor findById( long id);

}

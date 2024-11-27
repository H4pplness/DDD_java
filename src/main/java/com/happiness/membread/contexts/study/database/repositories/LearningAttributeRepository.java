package com.happiness.membread.contexts.study.database.repositories;

import com.happiness.membread.contexts.study.database.entities.LearningAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LearningAttributeRepository extends JpaRepository<LearningAttribute,String> {
    @Query(value = "SELECT * FROM learning_attribute WHERE learning_id = :learning_id",nativeQuery = true)
    List<LearningAttribute> getLearningAttributesByLearningId(@Param("learning_id") String id);
}

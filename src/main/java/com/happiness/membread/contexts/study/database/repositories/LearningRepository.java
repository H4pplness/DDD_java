package com.happiness.membread.contexts.study.database.repositories;

import com.happiness.membread.contexts.study.database.entities.Learning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LearningRepository extends JpaRepository<Learning,String> {
    @Query(value = "SELECT * FROM learning WHERE lesson_id = :lesson_id",nativeQuery = true)
    List<Learning> getLearningByLessonId(@Param("lesson_id") String lessonId);
}

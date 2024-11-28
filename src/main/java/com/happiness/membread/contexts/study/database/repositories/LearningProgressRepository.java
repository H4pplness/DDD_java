package com.happiness.membread.contexts.study.database.repositories;

import com.happiness.membread.contexts.study.database.entities.LearningProgress;
import com.happiness.membread.contexts.study.database.projections.GetLessonProgressProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LearningProgressRepository extends JpaRepository<LearningProgress,String> {
    @Query(
            value = """
            SELECT 
                learning.id AS learning_id, 
                lesson.id AS lesson_id, 
                learning_progress.user_id AS user_id, 
                learning_progress.progress, 
                learning_progress.last_time 
            FROM learning 
            JOIN lesson ON lesson.id = learning.lesson_id 
            JOIN learning_progress ON learning_progress.learning_id = learning.id 
            WHERE lesson.id = :lessonId 
            AND learning_progress.user_id = :userId
        """,
            nativeQuery = true
    )
    List<GetLessonProgressProjection> getLessonProgress(@Param("lessonId") String lessonId,@Param("userId") String userId);
}

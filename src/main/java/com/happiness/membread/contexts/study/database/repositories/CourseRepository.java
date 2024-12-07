package com.happiness.membread.contexts.study.database.repositories;

import com.happiness.membread.contexts.study.database.entities.Course;
import com.happiness.membread.contexts.study.database.projections.GetClassesOfCourseProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,String> {
    @Query(value = "SELECT id,title,description FROM clazz WHERE course_id = :id",nativeQuery = true)
    List<GetClassesOfCourseProjection> getClassesOfCourse(@Param("id") String id);

    @Query(value = "SELECT c1.* FROM course c1 JOIN course_category c2 ON c2.course_id = c1.id WHERE c2.category_id = :id",nativeQuery = true)
    List<Course> findCourseWithCategories(@Param("id") Integer id);
}

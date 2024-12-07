package com.happiness.membread.contexts.study.database.repositories;

import com.happiness.membread.contexts.study.database.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query(value = "SELECT c.* FROM category c JOIN course_category c1 ON c1.category_id = c.id WHERE c1.course_id = :id",nativeQuery = true)
    Set<Category> getCategoryOfCourse(@Param("id") String id);
}

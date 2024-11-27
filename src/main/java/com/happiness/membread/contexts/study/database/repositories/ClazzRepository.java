package com.happiness.membread.contexts.study.database.repositories;

import com.happiness.membread.contexts.study.database.projections.GetLessonsOfClazzProjection;
import com.happiness.membread.contexts.study.database.entities.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClazzRepository extends JpaRepository<Clazz,String> {
    @Query(value = "SELECT id,name,description,type FROM lesson WHERE clazz_id = :id",nativeQuery = true)
    List<GetLessonsOfClazzProjection> getLessonsOfClazz(@Param("id") String id);


}

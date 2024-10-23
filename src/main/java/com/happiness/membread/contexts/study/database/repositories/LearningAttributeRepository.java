package com.happiness.membread.contexts.study.database.repositories;

import com.happiness.membread.contexts.study.database.entities.LearningAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningAttributeRepository extends JpaRepository<LearningAttribute,String> {
}

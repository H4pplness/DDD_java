package com.happiness.membread.contexts.study.domain.aggregates.learnings;

import com.happiness.membread.contexts.study.database.entities.LearningAttribute;

import java.util.List;

public interface IConversion<T extends Learning> {
    T convertToLearning(List<LearningAttribute> attributes);
    List<LearningAttribute> convertToLearningAttribute(T learning);
}

package com.happiness.membread.contexts.study.domain.aggregates.learnings;

import com.happiness.membread.contexts.study.database.entities.LearningAttribute;

import java.util.List;

/**
 * To provide some methods to handle the conversion of specific learning
 * @param <T>
 */
public interface IConversion<T extends Learning> {
    /**
     * convert to a specific learning by attributes
     * @param attributes
     * @return
     */
    T convertToLearning(List<LearningAttribute> attributes);

    /**
     * convert to List<LearningAttribute> by a specific learning
     * @param learning
     * @return
     */
    List<LearningAttribute> convertToLearningAttribute(T learning);
}

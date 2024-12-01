package com.happiness.membread.contexts.study.domain.aggregates.learningmethods;

import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.methods.questionmethod.QuestionMethod;
import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.methods.vocabularymethod.SpacedRepetitionMethod;
import lombok.Getter;

@Getter
public enum LearningMethodType {
    VOCABULARY_SPACED_REPETITION("VOCABULARY_1", SpacedRepetitionMethod.class.getSimpleName()),
    QUESTION_REVIEW("QUESTION_1", QuestionMethod.class.getSimpleName())
    ;
    private final String method;
    private final String strategy;

    // Constructor
    LearningMethodType(String method, String strategy) {
        this.method = method;
        this.strategy = strategy;
    }

    // Static method to get strategy by method
    public static String getStrategyFromMethod(String method) {
        for (LearningMethodType type : LearningMethodType.values()) {
            if (type.method.equals(method)) {
                return type.strategy;
            }
        }
        throw new IllegalArgumentException("No strategy found for method: " + method);
    }
}

package com.happiness.membread.contexts.study.domain.aggregates.learningmethods;

import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.methods.questionmethod.QuestionMethod;
import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.methods.vocabularymethod.MatchingVocabularyGamificationMethod;
import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.methods.vocabularymethod.SpacedRepetitionMethod;
import lombok.Getter;

@Getter
public enum LearningMethodType {
    VOCABULARY_SPACED_REPETITION("VOCABULARY_1", SpacedRepetitionMethod.class.getSimpleName()),
    VOCABULARY_MATCHING_GAMIFICATION("VOCABULARY_2", MatchingVocabularyGamificationMethod.class.getSimpleName()),



    QUESTION_REVIEW("QUESTION_1", QuestionMethod.class.getSimpleName())



    ;
    private final String id;
    private final String method;

    // Constructor
    LearningMethodType(String id, String method) {
        this.id = id;
        this.method = method;
    }

    // Static method to get strategy by method
    public static String getStrategyFromMethod(String method) {
        for (LearningMethodType type : LearningMethodType.values()) {
            if (type.id.equals(method)) {
                return type.method;
            }
        }
        throw new IllegalArgumentException("No strategy found for method: " + method);
    }
}

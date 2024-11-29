package com.happiness.membread.contexts.study.domain.aggregates.learningmethods;

public interface ILearningMethod {
    LearningLesson learn(String lessonId,String userId);
}

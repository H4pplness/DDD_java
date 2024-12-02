package com.happiness.membread.contexts.study.domain.aggregates.learningmethods;

import java.util.List;

public interface ILearningMethod {
    LearningLesson learn(String lessonId,String userId);
}

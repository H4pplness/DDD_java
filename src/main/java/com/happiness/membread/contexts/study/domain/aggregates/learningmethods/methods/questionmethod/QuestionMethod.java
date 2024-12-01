package com.happiness.membread.contexts.study.domain.aggregates.learningmethods.methods.questionmethod;

import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.ILearningMethod;
import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.LearningLesson;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@AllArgsConstructor
public class QuestionMethod implements ILearningMethod {
    @Override
    public LearningLesson learn(String lessonId, String userId) {
        return null;
    }

    @Override
    public LearningLesson review(String lessonId, String userId, List<String> listLearning) {
        return null;
    }
}

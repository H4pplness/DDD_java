package com.happiness.membread.contexts.study.domain.aggregates.learningelements;

import java.util.List;

public interface ILearningService<T extends ILearning> {
    String getIConvertLearningStrategy();

    void saveListILearning(List<T> ilearningList);

    List<T> getLessonContent(String lessonId);
}

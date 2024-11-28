package com.happiness.membread.contexts.study.domain.aggregates.userprogress;

import java.util.List;

public interface ILessonProgress {
    LessonProgress getProgress(String userId,String lessonId);

    void updateProgress(LessonProgress lessonProgress);
}

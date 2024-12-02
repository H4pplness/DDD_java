package com.happiness.membread.contexts.study.domain.aggregates.userprogress;

import java.util.List;

public interface ILessonProgress {
    /**
     * Get user's progress of lesson
     * @param userId
     * @param lessonId
     * @return user's progress
     */
    LessonProgress getProgress(String userId,String lessonId);

    /**
     * Update user's progress after learning
     * @param lessonProgress
     */
    void updateProgress(LessonProgress lessonProgress);
}

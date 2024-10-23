package com.happiness.membread.contexts.study.domain.services;

import com.happiness.membread.contexts.study.domain.common.ILesson;

public interface ILessonService {
    void createLesson(ILesson lesson);
    ILesson getLesson(String id);
    void updateLesson(ILesson lesson);
}

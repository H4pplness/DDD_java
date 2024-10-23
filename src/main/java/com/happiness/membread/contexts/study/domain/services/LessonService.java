package com.happiness.membread.contexts.study.domain.services;

import com.happiness.membread.contexts.study.domain.common.ILesson;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("primaryLessonService")
@Primary
public class LessonService implements ILessonService{
    @Override
    public void createLesson(ILesson lesson) {

    }

    @Override
    public ILesson getLesson(String id) {
        return null;
    }

    @Override
    public void updateLesson(ILesson lesson) {

    }
}

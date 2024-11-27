package com.happiness.membread.contexts.study.domain.aggregates.lessons;

import com.happiness.membread.contexts.study.domain.aggregates.learnings.Learning;

import java.util.List;

public interface ILessonFactory<T extends Learning,E extends Lesson> {
    List<T> addListLearning(String lessonId, List<T> list);
    List<T> editListLearning(List<T> list);
    E getLesson(String id);
}

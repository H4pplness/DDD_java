package com.happiness.membread.contexts.study.domain.aggregates.lessons;

import com.happiness.membread.contexts.study.domain.aggregates.learnings.Learning;

import java.util.List;

/**
 * to provide an interface to operate for specific lessons (edit,get)
 */
public interface ILessonFactory<T extends Learning,E extends Lesson> {
    /**
     * @param lessonId
     * @param list - list learning to add
     * @return list
     */
    List<T> addListLearning(String lessonId, List<T> list);

    /**
     * @param list - list learning to edit
     * @return list
     */
    List<T> editListLearning(List<T> list);

    /**
     * @param id
     * @return get lesson by id
     */
    E getLesson(String id);
}

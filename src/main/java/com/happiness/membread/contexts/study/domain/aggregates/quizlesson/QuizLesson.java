package com.happiness.membread.contexts.study.domain.aggregates.quizlesson;

import com.happiness.membread.contexts.study.database.entities.Lesson;
import com.happiness.membread.contexts.study.domain.common.ILearning;
import com.happiness.membread.contexts.study.domain.common.ILesson;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuizLesson implements ILesson {
    Lesson lesson;
    List<ILearning> listQuestion;
}

package com.happiness.membread.contexts.study.domain.aggregates.lessons.question;

import com.happiness.membread.contexts.study.domain.aggregates.learnings.question.Question;
import com.happiness.membread.contexts.study.domain.aggregates.lessons.Lesson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class QuestionLesson extends Lesson {
    List<Question> listQuestion;
}

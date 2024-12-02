package com.happiness.membread.contexts.study.domain.aggregates.learningmethods.methods.questionmethod;

import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.LearningLesson;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.question.Question;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionLearningLesson extends LearningLesson {
    List<Question> listQuestion;
}

package com.happiness.membread.contexts.study.domain.aggregates.learningmethods.methods.vocabularymethod;

import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.LearningLesson;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.vocabulary.Vocabulary;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VocabularyLearningLesson extends LearningLesson {
    List<Vocabulary> vocabularyList;
}

package com.happiness.membread.contexts.study.domain.aggregates.lessons.vocabularylesson;

import com.happiness.membread.contexts.study.domain.aggregates.learnings.vocabulary.Vocabulary;
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
public class VocabularyLesson extends Lesson {
    List<Vocabulary> listVocabulary;
}

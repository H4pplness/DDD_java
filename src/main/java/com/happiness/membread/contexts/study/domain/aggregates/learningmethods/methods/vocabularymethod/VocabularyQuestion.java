package com.happiness.membread.contexts.study.domain.aggregates.learningmethods.methods.vocabularymethod;

import com.happiness.membread.contexts.study.domain.aggregates.learnings.vocabulary.Vocabulary;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VocabularyQuestion {
    Vocabulary vocabulary;
    List<Vocabulary> choices;
}

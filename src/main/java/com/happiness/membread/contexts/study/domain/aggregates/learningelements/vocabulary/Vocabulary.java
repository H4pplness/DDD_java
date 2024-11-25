package com.happiness.membread.contexts.study.domain.aggregates.learningelements.vocabulary;

import com.happiness.membread.contexts.study.domain.aggregates.learningelements.ILearning;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vocabulary implements ILearning {
    String learningId;
    String word;
    String meaning;
}

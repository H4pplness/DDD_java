package com.happiness.membread.contexts.study.domain.aggregates.vocabularylesson.learnings;

import com.happiness.membread.contexts.study.domain.common.ILearning;
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

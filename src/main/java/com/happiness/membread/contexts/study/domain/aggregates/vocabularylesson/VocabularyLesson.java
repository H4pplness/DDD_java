package com.happiness.membread.contexts.study.domain.aggregates.vocabularylesson;

import com.happiness.membread.contexts.study.database.entities.Lesson;
import com.happiness.membread.contexts.study.domain.common.ILearning;
import com.happiness.membread.contexts.study.domain.common.ILesson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyLesson implements ILesson {
    private Lesson lesson;
    private List<ILearning> vocabularies;
}

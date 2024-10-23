package com.happiness.membread.contexts.study.domain.aggregates.quizlesson.learnings.multichoicequestion;

import com.happiness.membread.contexts.study.database.entities.LearningAttribute;
import com.happiness.membread.contexts.study.domain.common.IConvertLearningStrategy;

import java.util.HashMap;
import java.util.List;

public class ConvertMultiChoiceQuestionStrategy implements IConvertLearningStrategy<MultiChoiceQuestion> {
    @Override
    public List<LearningAttribute> convertToLearningAttribute(MultiChoiceQuestion learning) {
        return List.of();
    }

    @Override
    public MultiChoiceQuestion getFromPureLearning(HashMap<String,String> listLearning) {
        return null;
    }
}

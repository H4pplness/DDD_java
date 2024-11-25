package com.happiness.membread.contexts.study.domain.aggregates.learningelements.multichoicequestion;

import com.happiness.membread.contexts.study.database.entities.LearningAttribute;
import com.happiness.membread.contexts.study.domain.aggregates.learningelements.IConvertLearningStrategy;

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

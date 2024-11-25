package com.happiness.membread.contexts.study.domain.aggregates.learningelements.fillquestion;

import com.happiness.membread.contexts.study.database.entities.LearningAttribute;
import com.happiness.membread.contexts.study.domain.aggregates.learningelements.IConvertLearningStrategy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConvertFillQuestionStrategy implements IConvertLearningStrategy<FillQuestion> {
    @Override
    public List<LearningAttribute> convertToLearningAttribute(FillQuestion learning) {
        List<LearningAttribute> listLearningAttribute = new ArrayList<>();

        Field[] fields = learning.getClass().getFields();
        for(Field field : fields){
            if(!field.getName().equals("learningId")){
                LearningAttribute learningAttribute = new LearningAttribute();
                learningAttribute.setLearningId(learning.getLearningId());
                learningAttribute.setAttribute(field.getName());
                try {
                    learningAttribute.setValue((String) field.get(learning));
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }

        return listLearningAttribute;
    }

    @Override
    public FillQuestion getFromPureLearning(HashMap<String,String> listLearning) {
        FillQuestion question = new FillQuestion();
        question.setQuestion(listLearning.get("question"));
        question.setAnswer(listLearning.get("answer"));
        question.setExplanation(listLearning.get("explanation"));
        question.setLearningId(listLearning.get("learningId"));

        return question;
    }
}

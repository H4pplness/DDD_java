package com.happiness.membread.contexts.study.domain.aggregates.learningelements.vocabulary;

import com.happiness.membread.contexts.study.database.entities.LearningAttribute;
import com.happiness.membread.contexts.study.domain.aggregates.learningelements.IConvertLearningStrategy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConvertVocabularyStrategy implements IConvertLearningStrategy<Vocabulary> {
    @Override
    public List<LearningAttribute> convertToLearningAttribute(Vocabulary learning) {
        System.out.println("CONVERT VOCABULARY STRATEGY");

        List<LearningAttribute> listLearningAttribute = new ArrayList<>();

        Field[] fields = learning.getClass().getDeclaredFields();
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
                listLearningAttribute.add(learningAttribute);
            }
        }

        return listLearningAttribute;
    }

    @Override
    public Vocabulary getFromPureLearning(HashMap<String,String> listLearning) {
        Vocabulary vocabulary = new Vocabulary();

        String learningId = listLearning.get("learningId");
        if(learningId!=null){
            vocabulary.setLearningId(learningId);
        }
        String word = listLearning.get("word");
        if(word!=null){
            vocabulary.setWord(word);
        }
        String meaning = listLearning.get("meaning");
        if(meaning!=null){
            vocabulary.setMeaning(meaning);
        }

        return vocabulary;
    }
}

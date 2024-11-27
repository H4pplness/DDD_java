package com.happiness.membread.contexts.study.domain.aggregates.learnings.vocabulary;

import com.happiness.membread.contexts.study.database.entities.LearningAttribute;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.IConversion;

import java.util.ArrayList;
import java.util.List;

public class VocabularyConversion implements IConversion<Vocabulary> {
    @Override
    public Vocabulary convertToLearning(List<LearningAttribute> attributes) {
        if (attributes.isEmpty())return null;
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setId(attributes.get(0).getLearningId());
        for (LearningAttribute attribute : attributes){
            switch (attribute.getAttribute()) {
                case "vocabulary" -> vocabulary.setVocabulary(attribute.getValue());
                case "meaning" -> vocabulary.setMeaning(attribute.getValue());
                case "image" -> vocabulary.setImage(attribute.getValue());
                case "sound" -> vocabulary.setSound(attribute.getValue());
            }
        }

        return vocabulary;
    }

    @Override
    public List<LearningAttribute> convertToLearningAttribute(Vocabulary learning) {
        List<LearningAttribute> attributes = new ArrayList<>();

        LearningAttribute vocabulary = new LearningAttribute();
        vocabulary.setLearningId(learning.getId());
        vocabulary.setAttribute("vocabulary");
        vocabulary.setValue(learning.getVocabulary());
        attributes.add(vocabulary);

        LearningAttribute meaning = new LearningAttribute();
        meaning.setLearningId(learning.getId());
        meaning.setAttribute("meaning");
        meaning.setValue(learning.getMeaning());
        attributes.add(meaning);

        if (learning.getImage() != null)
        {
            LearningAttribute image = new LearningAttribute();
            image.setLearningId(learning.getId());
            image.setAttribute("image");
            image.setValue(learning.getImage());
            attributes.add(image);
        }

        if(learning.getSound() != null){
            LearningAttribute sound = new LearningAttribute();
            sound.setLearningId(learning.getId());
            sound.setAttribute("sound");
            sound.setValue(learning.getSound());
            attributes.add(sound);
        }

        System.out.println(attributes);

        return attributes;
    }
}

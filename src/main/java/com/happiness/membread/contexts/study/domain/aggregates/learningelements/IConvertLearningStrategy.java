package com.happiness.membread.contexts.study.domain.aggregates.learningelements;

import com.happiness.membread.contexts.study.database.entities.LearningAttribute;

import java.util.HashMap;
import java.util.List;

public interface IConvertLearningStrategy<T extends ILearning> {
    /**
     * convert an ilearning to list learning attribute
     * @param learning
     * @return List<LearningAttribute> list of learning attribute in an ilearning
     */
    List<LearningAttribute> convertToLearningAttribute(T learning);

    /*
    * When get data from LearningAttributeEntity, attribute and value is String. So we take getFromPureLearning an argument Hashmap !!?
    * */
    T getFromPureLearning(HashMap<String,String> listLearning);
}

package com.happiness.membread.contexts.study.domain.aggregates.learningmethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class LearningLessonService {
    HashMap<String,ILearningMethod> learningMethodHashMap;

    @Autowired
    LearningLessonService(List<ILearningMethod> learningMethods){
        learningMethodHashMap = new HashMap<>();

        for (ILearningMethod method : learningMethods){
            learningMethodHashMap.put(method.getClass().getSimpleName(),method);
        }
    }

    public LearningLesson getLearningLesson(String lessonId,String userId,String method){
        String key = LearningMethodType.getStrategyFromMethod(method);
        ILearningMethod learningMethod = learningMethodHashMap.get(key);
        if (learningMethod == null)throw new IllegalArgumentException("Method not found !");

        return learningMethod.learn(lessonId,userId);
    }
}

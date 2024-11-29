package com.happiness.membread.contexts.study.domain.aggregates.learningmethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class LearningLessonService {
    HashMap<String,ILearningMethod> learningMethodHashMap;

    @Autowired
    LearningLessonService(List<ILearningMethod> methods){
        learningMethodHashMap = new HashMap<>();
        for (ILearningMethod method : methods){
            learningMethodHashMap.put(method.getClass().getSimpleName(),method);
        }
    }

    public LearningLesson getLearningLesson(String lessonId,String userId,String method){
        ILearningMethod learningMethod = learningMethodHashMap.get(method);
        if (learningMethod == null)throw new IllegalArgumentException("Method not found !");

        return learningMethod.learn(lessonId,userId);
    }
}

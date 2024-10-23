package com.happiness.membread.contexts.study.domain.common;

import com.happiness.membread.contexts.study.database.entities.LearningAttribute;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class ConvertLearning {
    private static final HashMap<String, IConvertLearningStrategy> strategies = new HashMap<>();

    static {
        Reflections reflections = new Reflections("com.happiness.membread");
        Set<Class<? extends IConvertLearningStrategy>> classes = reflections.getSubTypesOf(IConvertLearningStrategy.class);

        for(Class<? extends IConvertLearningStrategy> clazz : classes){
            try{
                String name = clazz.getName();
                IConvertLearningStrategy strategy = clazz.getDeclaredConstructor().newInstance();
                strategies.put(name,strategy);
            }catch (Exception e){
                throw new RuntimeException("Error during strategy initialization");
            }
        }
    }

    public List<LearningAttribute> convertToListLearning(String strategy,List<ILearning> list){
        IConvertLearningStrategy strategyInstance = strategies.get(strategy);
        if(strategyInstance == null){
            throw new RuntimeException("Not found strategy");
        }else{
            List<LearningAttribute> listLearning = new ArrayList<>();
            for(ILearning learning : list){
                List<LearningAttribute> learningEntities = strategyInstance.convertToLearningAttribute(learning);
                listLearning.addAll(learningEntities);
            }
            return listLearning;
        }
    }

    public List<ILearning> convertToListILearning(String strategy,List<LearningAttribute> list){
        IConvertLearningStrategy strategyInstance = strategies.get(strategy);
        if(strategyInstance == null){
            throw new RuntimeException("Not found strategy");
        }else{
            List<ILearning> listILearning = new ArrayList<>();

            Collections.sort(list,Comparator.comparing(LearningAttribute::getLearningId));
            HashMap<String,String> learningAttributes = new HashMap<>();
            LearningAttribute pre = list.get(0);
            learningAttributes.put("learningId",pre.getLearningId());
            for(LearningAttribute attribute : list){
                if(pre.getId() == attribute.getId()){
                    learningAttributes.put(attribute.getAttribute(),attribute.getValue());
                }else{
                    pre = attribute;
                    ILearning learningInstance = strategyInstance.getFromPureLearning(learningAttributes);
                    listILearning.add(learningInstance);
                    learningAttributes.clear();
                    learningAttributes.put(attribute.getAttribute(),attribute.getValue());
                    learningAttributes.put("learningId",attribute.getLearningId());
                }
            }
            return listILearning;
        }
    }
}

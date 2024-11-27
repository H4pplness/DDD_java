package com.happiness.membread.contexts.study.domain.aggregates.learnings;

import com.happiness.membread.contexts.study.database.entities.LearningAttribute;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class LearningConversion {
    private static final HashMap<String,IConversion> map = new HashMap<>();

    static {
        Reflections reflections = new Reflections("com.happiness.membread");
        Set<Class<? extends IConversion>> classes = reflections.getSubTypesOf(IConversion.class);

        for (Class<? extends IConversion> clazz : classes){
            try {
                String name = clazz.getName();
                System.out.println("NAME : " +name);
                IConversion conversion = clazz.getDeclaredConstructor().newInstance();
                map.put(name,conversion);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    public Learning convertToLearning(String conversion,List<LearningAttribute> attributes){
        IConversion strategy = map.get(conversion);
        if (strategy == null){
            throw new IllegalArgumentException("Conversion " + conversion + " not found !");
        }
        return strategy.convertToLearning(attributes);
    }

    public List<LearningAttribute> convertToLearningAttributes(String conversion,Learning learning){
        IConversion strategy = map.get(conversion);
        if (strategy == null){
            throw new IllegalArgumentException("Conversion " + conversion + " not found !");
        }

        return strategy.convertToLearningAttribute(learning);
    }


}

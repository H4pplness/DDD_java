package com.happiness.membread.contexts.study.domain.aggregates.lessons;

import com.happiness.membread.contexts.study.database.repositories.LessonRepository;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.Learning;
import com.happiness.membread.contexts.study.domain.aggregates.lessons.question.QuestionLessonFactory;
import com.happiness.membread.contexts.study.domain.aggregates.lessons.vocabularylesson.VocabularyLessonFactory;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class LessonFactory {
    private static final HashMap<String,ILessonFactory> map = new HashMap<>();

    private final LessonRepository lessonRepository;

    @Autowired
    public LessonFactory(List<ILessonFactory> factories,LessonRepository lessonRepository){
        this.lessonRepository = lessonRepository;
        for (ILessonFactory factory : factories){
            map.put(factory.getClass().getSimpleName(),factory);
        }
    }

    public Lesson getLesson(String id){
        ILessonFactory factory = getStrategy(id);
        return factory.getLesson(id);
    }

    public List<Learning> addLearning(String lessonId,List<Learning> list){
        ILessonFactory factory = getStrategy(lessonId);
        return factory.addListLearning(lessonId,list);
    }

    public List<Learning> editLearning(String lessonId,List<Learning> list){
        ILessonFactory factory = getStrategy(lessonId);
        return factory.editListLearning(list);
    }

    private ILessonFactory getStrategy(String id){
        com.happiness.membread.contexts.study.database.entities.Lesson lesson = lessonRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Value not found"));

        String type = lesson.getType();
        String strategy = switch (type) {
            case "vocabulary" -> VocabularyLessonFactory.class.getSimpleName();
            case "video" -> "VideoLessonFactory";
            case "question" -> QuestionLessonFactory.class.getSimpleName();
            default -> "";
        };

        System.out.println("Strategy : "+strategy);

        ILessonFactory factory = map.get(strategy);
        if (factory == null)throw new IllegalArgumentException("Invalid lesson !");

        return factory;
    }
}

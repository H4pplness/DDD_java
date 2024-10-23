package com.happiness.membread.contexts.study.domain.aggregates.vocabularylesson.learnings;

import com.happiness.membread.contexts.study.domain.common.ILearningService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VocabularyService implements ILearningService<Vocabulary> {
    @Override
    public String getIConvertLearningStrategy() {
        return ConvertVocabularyStrategy.class.getName();
    }

    @Override
    public void saveListILearning(List<Vocabulary> ilearningList) {

    }

    @Override
    public List<Vocabulary> getLessonContent(String lessonId) {
        return List.of();
    }
}

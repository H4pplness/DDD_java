package com.happiness.membread.contexts.study.domain.aggregates.lessons.vocabularylesson;

import com.happiness.membread.contexts.study.database.entities.Learning;
import com.happiness.membread.contexts.study.database.entities.LearningAttribute;
import com.happiness.membread.contexts.study.database.entities.Lesson;
import com.happiness.membread.contexts.study.database.repositories.LearningAttributeRepository;
import com.happiness.membread.contexts.study.database.repositories.LearningRepository;
import com.happiness.membread.contexts.study.database.repositories.LessonRepository;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.LearningConversion;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.vocabulary.Vocabulary;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.vocabulary.VocabularyConversion;
import com.happiness.membread.contexts.study.domain.aggregates.lessons.ILessonFactory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VocabularyLessonFactory implements ILessonFactory<Vocabulary,VocabularyLesson> {
    LearningAttributeRepository learningAttributeRepository;
    LearningRepository learningRepository;
    LessonRepository lessonRepository;
    LearningConversion learningConversion;

    @Override
    public List<Vocabulary> addListLearning(String lessonId,List<Vocabulary> list) {
        for (Vocabulary vocabulary : list){
            Learning learning = new Learning();
            learning.setLessonId(lessonId);
            learning.setType("vocabulary");
            learningRepository.save(learning);

            vocabulary.setId(learning.getId());
            List<LearningAttribute> attributes = learningConversion.convertToLearningAttributes(VocabularyConversion.class.getName(),vocabulary);
            learningAttributeRepository.saveAll(attributes);
        }

        return list;
    }

    @Override
    public List<Vocabulary> editListLearning(List<Vocabulary> list) {
        return List.of();
    }

    @Override
    public VocabularyLesson getLesson(String id) {
        VocabularyLesson vocabularyLesson = new VocabularyLesson();
        List<Vocabulary> listVocabulary = new ArrayList<>();

        Lesson lesson = lessonRepository.getReferenceById(id);
        vocabularyLesson.setId(lesson.getId());
        vocabularyLesson.setTitle(lesson.getName());
        vocabularyLesson.setDescription(lesson.getDescription());

        List<Learning> listLearning = learningRepository.getLearningByLessonId(id);
        for (Learning learning : listLearning){
            List<LearningAttribute> attributes = learningAttributeRepository.getLearningAttributesByLearningId(learning.getId());
            Vocabulary vocabulary = (Vocabulary) learningConversion.convertToLearning(VocabularyConversion.class.getName(),attributes);
            vocabulary.setId(learning.getId());
            listVocabulary.add(vocabulary);
        }

        vocabularyLesson.setListVocabulary(listVocabulary);

        return vocabularyLesson;
    }
}

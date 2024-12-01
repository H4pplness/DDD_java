package com.happiness.membread.contexts.study.domain.aggregates.lessons.question;

import com.happiness.membread.contexts.study.database.entities.Learning;
import com.happiness.membread.contexts.study.database.entities.LearningAttribute;
import com.happiness.membread.contexts.study.database.entities.Lesson;
import com.happiness.membread.contexts.study.database.repositories.LearningAttributeRepository;
import com.happiness.membread.contexts.study.database.repositories.LearningRepository;
import com.happiness.membread.contexts.study.database.repositories.LessonRepository;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.LearningConversion;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.question.*;
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
public class QuestionLessonFactory implements ILessonFactory<Question,QuestionLesson> {
    LearningAttributeRepository learningAttributeRepository;
    LearningRepository learningRepository;
    LessonRepository lessonRepository;
    LearningConversion learningConversion;

    @Override
    public List<Question> addListLearning(String lessonId, List<Question> list) {
        for (Question question : list){
            Learning learning = new Learning();
            learning.setLessonId(lessonId);

            if (question instanceof FillQuestion){
                learning.setType("fillquestion");
            }else if (question instanceof MultiChoiceQuestion){
                learning.setType("multichoicequestion");
            }else{
                learning.setType("question");
            }

            learningRepository.save(learning);

            question.setId(learning.getId());
            List<LearningAttribute> attributes;
            switch (learning.getType()){
                case "fillquestion" -> attributes = learningConversion.convertToLearningAttributes(FillQuestionConversion.class.getName(),question);
                case "multichoicequestion" -> attributes = learningConversion.convertToLearningAttributes(MultiChoiceQuestionConversion.class.getName(),question);
                default -> throw new RuntimeException("Not found type !");
            }
            learningAttributeRepository.saveAll(attributes);
        }

        return list;
    }

    @Override
    public List<Question> editListLearning(List<Question> list) {
        return List.of();
    }

    @Override
    public QuestionLesson getLesson(String id) {
        QuestionLesson questionLesson = new QuestionLesson();
        List<Question> listQuestion = new ArrayList<>();

        Lesson lesson = lessonRepository.getReferenceById(id);
        questionLesson.setId(lesson.getId());
        questionLesson.setTitle(lesson.getName());
        questionLesson.setDescription(lesson.getDescription());

        List<Learning> listLearning = learningRepository.getLearningByLessonId(id);
        for (Learning learning : listLearning){
            List<LearningAttribute> attributes = learningAttributeRepository.getLearningAttributesByLearningId(learning.getId());
            Question question;
            switch (learning.getType()){
                case "fillquestion"-> question = (FillQuestion) learningConversion.convertToLearning(FillQuestionConversion.class.getName(),attributes);
                case "multichoicequestion" -> question = (MultiChoiceQuestion) learningConversion.convertToLearning(MultiChoiceQuestionConversion.class.getName(),attributes);
                default -> throw new RuntimeException("Not found type !");
            }

            if (question != null) listQuestion.add(question);
        }

        questionLesson.setListQuestion(listQuestion);

        return questionLesson;
    }
}

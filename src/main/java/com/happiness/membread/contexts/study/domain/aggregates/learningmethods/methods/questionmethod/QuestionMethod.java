package com.happiness.membread.contexts.study.domain.aggregates.learningmethods.methods.questionmethod;

import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.ILearningMethod;
import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.LearningLesson;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.question.Question;
import com.happiness.membread.contexts.study.domain.aggregates.lessons.LessonFactory;
import com.happiness.membread.contexts.study.domain.aggregates.lessons.question.QuestionLesson;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@AllArgsConstructor
public class QuestionMethod implements ILearningMethod {
    final static private int NUMBER_OF_QUESTIONS = 10;

    LessonFactory lessonFactory;

    @Override
    public QuestionLearningLesson learn(String lessonId, String userId) {
        QuestionLesson lesson = (QuestionLesson) lessonFactory.getLesson(lessonId);

        Random random = new Random();

        List<Question> listQuestion = lesson.getListQuestion();
        int numberOfQuestion = Math.min(listQuestion.size(),NUMBER_OF_QUESTIONS);

        Set<Question> questionSet = new HashSet<>();

        for (int i = 0;i<numberOfQuestion;i++){
            while (true){
                int randomIdx = random.nextInt(numberOfQuestion);
                Question question = listQuestion.get(randomIdx);
                if (!questionSet.contains(question)){
                    questionSet.add(question);
                    break;
                }
            }
        }

        List<Question> randomListQuestion = new ArrayList<>(questionSet.stream().toList());
        Collections.shuffle(randomListQuestion);

        QuestionLearningLesson learningLesson = new QuestionLearningLesson();
        learningLesson.setId(lessonId);
        learningLesson.setListQuestion(randomListQuestion);

        return learningLesson;
    }
}

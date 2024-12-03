package com.happiness.membread.contexts.study.domain.aggregates.learningmethods.methods.vocabularymethod;

import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.ILearningMethod;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.vocabulary.Vocabulary;
import com.happiness.membread.contexts.study.domain.aggregates.lessons.Lesson;
import com.happiness.membread.contexts.study.domain.aggregates.lessons.LessonFactory;
import com.happiness.membread.contexts.study.domain.aggregates.lessons.vocabularylesson.VocabularyLesson;
import com.happiness.membread.contexts.study.domain.aggregates.userprogress.LessonProgress;
import com.happiness.membread.contexts.study.domain.aggregates.userprogress.LessonProgressService;
import com.happiness.membread.contexts.study.domain.aggregates.userprogress.UserLearningProgress;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class SpacedRepetitionMethod implements ILearningMethod {
    LessonFactory lessonFactory;

    LessonProgressService lessonProgressService;

    static class VocabularyLearningProgress {
        Vocabulary vocabulary;
        double score = Double.MAX_VALUE;
    }

    @Override
    public VocabularyLearningLesson learn(String lessonId, String userId) {
        Lesson gotLesson = lessonFactory.getLesson(lessonId);
        if (!(gotLesson instanceof VocabularyLesson lesson)){
            throw new IllegalArgumentException("Invalid method !");
        }

        LessonProgress lessonProgress = lessonProgressService.getProgress(userId,lessonId);

        HashMap<String,VocabularyLearningProgress> map = new HashMap<>();
        for (Vocabulary vocabulary : lesson.getListVocabulary()){
            VocabularyLearningProgress temp = new VocabularyLearningProgress();
            temp.vocabulary = vocabulary;
            map.put(vocabulary.getId(), temp);
        }

        for (UserLearningProgress progress : lessonProgress.getUserProgress()){
            VocabularyLearningProgress temp = map.get(progress.getLearningId());
            Duration duration = Duration.between(progress.getLastUpdated(),LocalDateTime.now());
            long hours = duration.toHours();
            double res = (double) hours / (double) progress.getProgress();
            temp.score = Math.exp(res);
        }

        List<VocabularyLearningProgress> sortedList = new ArrayList<>(map.values());
        sortedList.sort(Comparator.comparingDouble(a -> a.score));
        Collections.reverse(sortedList);

        List<Vocabulary> listVocabulary = sortedList.stream()
                .map(e->e.vocabulary).toList()
                .subList(0,Math.min(20,sortedList.size()));

        List<VocabularyQuestion> vocabularyQuestionList = new ArrayList<>();

        List<Vocabulary> lessonVocabs = lesson.getListVocabulary();
        int length = lessonVocabs.size();
        Random random = new Random();
        for (Vocabulary vocabulary : listVocabulary){
            Set<Vocabulary> choices = new HashSet<>();
            choices.add(vocabulary);

            for (int i=0;i<3;i++){
                while (true){
                    int randomInt = random.nextInt(length);
                    Vocabulary randomVocab = lessonVocabs.get(randomInt);
                    if (!choices.contains(randomVocab)){
                        choices.add(randomVocab);
                        break;
                    }
                }
            }
            VocabularyQuestion question = new VocabularyQuestion();
            question.setVocabulary(vocabulary);
            List<Vocabulary> listQuestion = new ArrayList<>(choices.stream().toList());
            Collections.shuffle(listQuestion);
            question.setChoices(listQuestion);

            vocabularyQuestionList.add(question);
        }

        VocabularyLearningLesson learningLesson = new VocabularyLearningLesson();
        learningLesson.setVocabularyQuestions(vocabularyQuestionList);
        learningLesson.setId(lessonId);

        return learningLesson;
    }
}

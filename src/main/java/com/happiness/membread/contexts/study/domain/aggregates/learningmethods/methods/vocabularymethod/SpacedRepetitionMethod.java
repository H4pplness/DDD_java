package com.happiness.membread.contexts.study.domain.aggregates.learningmethods.methods.vocabularymethod;

import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.ILearningMethod;
import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.LearningLesson;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.vocabulary.Vocabulary;
import com.happiness.membread.contexts.study.domain.aggregates.lessons.LessonFactory;
import com.happiness.membread.contexts.study.domain.aggregates.lessons.vocabularylesson.VocabularyLesson;
import com.happiness.membread.contexts.study.domain.aggregates.userprogress.LessonProgress;
import com.happiness.membread.contexts.study.domain.aggregates.userprogress.LessonProgressService;
import com.happiness.membread.contexts.study.domain.aggregates.userprogress.UserLearningProgress;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@AllArgsConstructor
public class SpacedRepetitionMethod implements ILearningMethod {
    LessonFactory lessonFactory;

    LessonProgressService lessonProgressService;

    static class VocabularyLearningProgress {
        Vocabulary vocabulary;
        double score = Double.MAX_VALUE;
    }

    @Override
    public VocabularyLearningLesson learn(String lessonId, String userId) {
        VocabularyLesson lesson = (VocabularyLesson) lessonFactory.getLesson(lessonId);
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

        VocabularyLearningLesson learningLesson = new VocabularyLearningLesson();
        learningLesson.setVocabularyList(listVocabulary);
        learningLesson.setId(lessonId);

        return learningLesson;
    }

    @Override
    public LearningLesson review(String lessonId, String userId, List<String> listLearning) {
        return null;
    }
}

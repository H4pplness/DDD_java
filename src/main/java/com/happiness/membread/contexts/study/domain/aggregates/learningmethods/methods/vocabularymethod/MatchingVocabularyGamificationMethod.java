package com.happiness.membread.contexts.study.domain.aggregates.learningmethods.methods.vocabularymethod;

import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.ILearningMethod;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.vocabulary.Vocabulary;
import com.happiness.membread.contexts.study.domain.aggregates.lessons.LessonFactory;
import com.happiness.membread.contexts.study.domain.aggregates.lessons.vocabularylesson.VocabularyLesson;
import com.happiness.membread.contexts.study.domain.aggregates.userprogress.LessonProgress;
import com.happiness.membread.contexts.study.domain.aggregates.userprogress.LessonProgressService;
import com.happiness.membread.contexts.study.domain.aggregates.userprogress.UserLearningProgress;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class MatchingVocabularyGamificationMethod implements ILearningMethod {
    private static final int NUMBER_OF_VOCABS = 10;

    LessonFactory lessonFactory;

    LessonProgressService lessonProgressService;

    @Override
    public MatchingVocabularyLesson learn(String lessonId, String userId) {
        VocabularyLesson vocabularyLesson = (VocabularyLesson) lessonFactory.getLesson(lessonId);
        LessonProgress lessonProgress = lessonProgressService.getProgress(userId,lessonId);

        List<UserLearningProgress> learnedVocabs = lessonProgress.getUserProgress();
        Set<String> learningIdSet = new HashSet<>();

        int numberOfVocabs = Math.min(NUMBER_OF_VOCABS,learnedVocabs.size());

        Random random = new Random();
        for (int i=0;i<numberOfVocabs;i++){
            while (true){
                int randomInteger = random.nextInt(numberOfVocabs);
                String learningId = learnedVocabs.get(randomInteger).getLearningId();
                if (!learningIdSet.contains(learningId)){
                    learningIdSet.add(learningId);
                    break;
                }
            }
        }

        List<Vocabulary> listVocabulary = new ArrayList<>();

        for (Vocabulary vocabulary : vocabularyLesson.getListVocabulary()){
            if (learningIdSet.contains(vocabulary.getId())){
                listVocabulary.add(vocabulary);
            }
        }

        MatchingVocabularyLesson learningLesson = new MatchingVocabularyLesson();
        learningLesson.setId(lessonId);
        learningLesson.setListVocabulary(listVocabulary);

        return learningLesson;
    }
}

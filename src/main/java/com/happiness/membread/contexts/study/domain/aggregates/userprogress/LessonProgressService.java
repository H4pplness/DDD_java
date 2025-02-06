package com.happiness.membread.contexts.study.domain.aggregates.userprogress;

import com.happiness.membread.contexts.study.database.entities.LearningProgress;
import com.happiness.membread.contexts.study.database.projections.GetLessonProgressProjection;
import com.happiness.membread.contexts.study.database.repositories.LearningProgressRepository;
import com.happiness.membread.contexts.study.database.repositories.LessonRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Primary
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class LessonProgressService implements ILessonProgress{
    LearningProgressRepository learningProgressRepository;

    @Override
    public LessonProgress getProgress(String userId, String lessonId) {
        List<GetLessonProgressProjection> userProgress = learningProgressRepository.getLessonProgress(lessonId,userId);
        System.out.println(userProgress.size());
        List<UserLearningProgress> listProgress = userProgress.stream().map(e->{
            UserLearningProgress progress = new UserLearningProgress();
            progress.setProgress(e.getProgress());
            progress.setLastUpdated(e.getLastTime());
            progress.setLearningId(e.getLearningId());
            return progress;
        }).toList();

        LessonProgress lessonProgress = new LessonProgress();
        lessonProgress.setLessonId(lessonId);
        lessonProgress.setUserId(userId);
        lessonProgress.setUserProgress(listProgress);

        return lessonProgress;
    }

    @Override
    public void updateProgress(LessonProgress lessonProgress) {
        List<LearningProgress> learningProgressList = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();
        String userId = lessonProgress.getUserId();

        for (UserLearningProgress userLearningProgress : lessonProgress.getUserProgress()){
            LearningProgress learningProgress = new LearningProgress();
            learningProgress.setProgress(userLearningProgress.getProgress());
            learningProgress.setUserId(userId);
            learningProgress.setLastTime(now);
            learningProgress.setLearningId(userLearningProgress.getLearningId());
            learningProgressList.add(learningProgress);
        }

        learningProgressRepository.saveAll(learningProgressList);
    }
}

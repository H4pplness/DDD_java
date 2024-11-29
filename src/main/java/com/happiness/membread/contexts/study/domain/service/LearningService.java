package com.happiness.membread.contexts.study.domain.service;

import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.LearningLesson;
import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.LearningLessonService;
import com.happiness.membread.contexts.study.domain.aggregates.userprogress.ILessonProgress;
import com.happiness.membread.contexts.study.domain.aggregates.userprogress.LessonProgress;
import com.happiness.membread.contexts.study.domain.aggregates.userprogress.UserLearningProgress;
import com.happiness.membread.contexts.study.domain.dtos.UpdateLearningProgressRequestDto;
import com.happiness.membread.contexts.study.domain.dtos.UpdateLessonProgressRequestDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class LearningService {
    ILessonProgress lessonProgress;

    LearningLessonService learningLessonService;

    public LessonProgress getLessonProgress(String lessonId,String userId){
        return lessonProgress.getProgress(userId,lessonId);
    }

    public void getClazzProgress(String clazzId,String userId){

    }

    public void updateLessonProgress(String userId,UpdateLessonProgressRequestDto progress){
        LessonProgress lessonProgressArg = new LessonProgress();
        lessonProgressArg.setLessonId(progress.getLessonId());
        lessonProgressArg.setUserId(userId);

        List<UserLearningProgress> userLearningProgresses = new ArrayList<>();

        for (UpdateLearningProgressRequestDto learningProgress : progress.getListUpdated()){
            UserLearningProgress userLearningProgress = new UserLearningProgress();
            userLearningProgress.setProgress(learningProgress.getProgress());
            userLearningProgress.setLearningId(learningProgress.getLearningId());

            userLearningProgresses.add(userLearningProgress);
        }

        lessonProgressArg.setUserProgress(userLearningProgresses);

        lessonProgress.updateProgress(lessonProgressArg);
    }

    public LearningLesson studyLesson(String lessonId,String userId,String method){
        return learningLessonService.getLearningLesson(lessonId,userId,method);
    }

}

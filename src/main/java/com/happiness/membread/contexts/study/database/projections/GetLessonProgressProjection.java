package com.happiness.membread.contexts.study.database.projections;

import java.time.LocalDateTime;

public interface GetLessonProgressProjection {
    String getUserId();
    String getLearningId();
    String getLessonId();
    int getProgress();
    LocalDateTime getLastTime();
}

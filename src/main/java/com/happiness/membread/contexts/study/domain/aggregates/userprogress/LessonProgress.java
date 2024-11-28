package com.happiness.membread.contexts.study.domain.aggregates.userprogress;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonProgress {
    String userId;
    String lessonId;
    List<UserLearningProgress> userProgress;
}

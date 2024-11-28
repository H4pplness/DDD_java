package com.happiness.membread.contexts.study.domain.aggregates.userprogress;

import com.happiness.membread.contexts.study.domain.aggregates.learnings.Learning;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLearningProgress {
    String learningId;
    int progress;
    LocalDateTime lastUpdated;
}

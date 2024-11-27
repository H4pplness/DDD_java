package com.happiness.membread.contexts.study.domain.dtos;

import com.happiness.membread.contexts.study.domain.aggregates.learnings.Learning;
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
public class AddLearningToNewLessonRequestDto {
    String id;
    List<Learning> list;
}

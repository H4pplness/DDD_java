package com.happiness.membread.contexts.study.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLessonProgressRequestDto {
    @NotNull
    String lessonId;

    List<UpdateLearningProgressRequestDto> listUpdated;
}


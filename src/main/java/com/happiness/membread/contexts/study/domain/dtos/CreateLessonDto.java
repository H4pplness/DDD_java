package com.happiness.membread.contexts.study.domain.dtos;

import com.happiness.membread.contexts.study.domain.aggregates.learningelements.ILearning;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CreateLessonDto {
    @NotEmpty
    String title;

    String description;

    String type;

    List<ILearning> listLearning;
}

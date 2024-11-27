package com.happiness.membread.contexts.study.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateLessonRequestDto {
    @NotNull
    String name;

    String description;

    @NotNull
    String type;

    @NotNull
    String clazz_id;
}

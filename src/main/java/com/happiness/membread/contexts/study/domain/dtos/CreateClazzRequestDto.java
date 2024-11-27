package com.happiness.membread.contexts.study.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateClazzRequestDto {
    @NotNull
    String name;

    @NotNull
    String description;
}

package com.happiness.membread.contexts.study.domain.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCategoryRequestDto {
    String name;
    String description;
}

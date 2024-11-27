package com.happiness.membread.contexts.study.domain.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonSummaryResponseDto {
    String id;
    String name;
    String type;
}

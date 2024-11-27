package com.happiness.membread.contexts.study.domain.aggregates.lessons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Lesson {
    String id;
    String title;
    String description;
    String clazzId;
}

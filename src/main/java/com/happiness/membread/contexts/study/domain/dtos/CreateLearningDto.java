package com.happiness.membread.contexts.study.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * convert to learningDetail to save data, get data and update data from database
 */
@Data
@AllArgsConstructor
@Builder
public class CreateLearningDto {
    private String lessonId;
    private String attribute;
    private String value;
}

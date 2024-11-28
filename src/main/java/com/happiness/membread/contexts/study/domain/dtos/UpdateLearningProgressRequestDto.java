package com.happiness.membread.contexts.study.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLearningProgressRequestDto{
    String learningId;
    int progress;
}

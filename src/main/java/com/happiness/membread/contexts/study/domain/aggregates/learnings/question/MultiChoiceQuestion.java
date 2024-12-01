package com.happiness.membread.contexts.study.domain.aggregates.learnings.question;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.Learning;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MultiChoiceQuestion extends Question {
    List<String> listChoice;
    List<String> correctChoice;
}


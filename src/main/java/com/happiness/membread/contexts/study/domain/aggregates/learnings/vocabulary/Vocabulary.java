package com.happiness.membread.contexts.study.domain.aggregates.learnings.vocabulary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.Learning;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vocabulary extends Learning {
    String vocabulary;
    String meaning;
    String image;
    String sound;
}

package com.happiness.membread.contexts.study.domain.aggregates.quizlesson.learnings.multichoicequestion;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class MultiChoiceQuestion implements com.happiness.membread.contexts.study.domain.aggregates.quizlesson.learnings.IQuestion {
    private String learningId;
    private List<Option> listQuestion;
    private String explanation;
}


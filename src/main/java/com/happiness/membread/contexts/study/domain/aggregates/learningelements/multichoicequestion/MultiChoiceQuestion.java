package com.happiness.membread.contexts.study.domain.aggregates.learningelements.multichoicequestion;

import com.happiness.membread.contexts.study.domain.aggregates.learningelements.IQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class MultiChoiceQuestion implements IQuestion {
    private String learningId;
    private List<Option> listQuestion;
    private String explanation;
}


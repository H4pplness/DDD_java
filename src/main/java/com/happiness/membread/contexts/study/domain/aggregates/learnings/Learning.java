package com.happiness.membread.contexts.study.domain.aggregates.learnings;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.question.FillQuestion;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.question.MultiChoiceQuestion;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.vocabulary.Vocabulary;
import lombok.Data;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Vocabulary.class, name = "vocabulary"),
        @JsonSubTypes.Type(value = FillQuestion.class, name = "fillquestion"),
        @JsonSubTypes.Type(value = MultiChoiceQuestion.class, name = "multichoicequestion")
})
public abstract class Learning {
    String id;
    public Learning(){}
}

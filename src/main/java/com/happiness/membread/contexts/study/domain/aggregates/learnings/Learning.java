package com.happiness.membread.contexts.study.domain.aggregates.learnings;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.vocabulary.Vocabulary;
import lombok.Data;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Vocabulary.class, name = "vocabulary")
})
public abstract class Learning {
    String id;
    public Learning(){}
}

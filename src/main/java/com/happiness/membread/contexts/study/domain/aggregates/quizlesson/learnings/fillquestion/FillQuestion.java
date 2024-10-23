package com.happiness.membread.contexts.study.domain.aggregates.quizlesson.learnings.fillquestion;

import com.happiness.membread.contexts.study.domain.aggregates.quizlesson.learnings.IQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FillQuestion implements IQuestion {
    private String learningId;
    private String question;
    private String answer;
    private String explanation;
}

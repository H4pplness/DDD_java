package com.happiness.membread.contexts.study.domain.aggregates.learnings.question;

import com.happiness.membread.contexts.study.database.entities.LearningAttribute;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.IConversion;

import java.util.ArrayList;
import java.util.List;

public class FillQuestionConversion implements IConversion<FillQuestion> {
    @Override
    public FillQuestion convertToLearning(List<LearningAttribute> attributes) {
        if (attributes.isEmpty())return null;
        FillQuestion fillQuestion = new FillQuestion();
        fillQuestion.setId(attributes.get(0).getLearningId());
        for (LearningAttribute attribute : attributes){
            switch (attribute.getAttribute()) {
                case "question" -> fillQuestion.setQuestion(attribute.getValue());
                case "answer" -> fillQuestion.setAnswer(attribute.getValue());
                case "explanation" -> fillQuestion.setExplanation(attribute.getValue());
                case "image" -> fillQuestion.setImage(attribute.getValue());
            }
        }

        return fillQuestion;
    }

    @Override
    public List<LearningAttribute> convertToLearningAttribute(FillQuestion learning) {
        List<LearningAttribute> attributes = new ArrayList<>();

        LearningAttribute question = new LearningAttribute();
        question.setLearningId(learning.getId());
        question.setAttribute("question");
        question.setValue(learning.getQuestion());
        attributes.add(question);

        LearningAttribute answer = new LearningAttribute();
        answer.setLearningId(learning.getId());
        answer.setAttribute("answer");
        answer.setValue(learning.getAnswer());
        attributes.add(answer);

        if (learning.getImage() != null)
        {
            LearningAttribute image = new LearningAttribute();
            image.setLearningId(learning.getId());
            image.setAttribute("image");
            image.setValue(learning.getImage());
            attributes.add(image);
        }

        if(learning.getExplanation() != null){
            LearningAttribute explanation = new LearningAttribute();
            explanation.setLearningId(learning.getId());
            explanation.setAttribute("explanation");
            explanation.setValue(learning.getExplanation());
            attributes.add(explanation);
        }

        System.out.println(attributes);

        return attributes;
    }
}

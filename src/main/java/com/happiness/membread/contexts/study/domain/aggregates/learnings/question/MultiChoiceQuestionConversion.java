package com.happiness.membread.contexts.study.domain.aggregates.learnings.question;

import com.happiness.membread.contexts.study.database.entities.LearningAttribute;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.IConversion;

import java.util.ArrayList;
import java.util.List;

public class MultiChoiceQuestionConversion implements IConversion<MultiChoiceQuestion> {
    @Override
    public MultiChoiceQuestion convertToLearning(List<LearningAttribute> attributes) {
        if (attributes.isEmpty())return null;
        MultiChoiceQuestion multichoiceQuestion = new MultiChoiceQuestion();
        multichoiceQuestion.setId(attributes.get(0).getLearningId());
        multichoiceQuestion.setListChoice(new ArrayList<>());
        multichoiceQuestion.setCorrectChoice(new ArrayList<>());
        for (LearningAttribute attribute : attributes){
            switch (attribute.getAttribute()) {
                case "question" -> multichoiceQuestion.setQuestion(attribute.getValue());
                case "choice" -> multichoiceQuestion.getListChoice().add(attribute.getValue());
                case "explanation" -> multichoiceQuestion.setExplanation(attribute.getValue());
                case "image" -> multichoiceQuestion.setImage(attribute.getValue());
                case "correct" -> multichoiceQuestion.getCorrectChoice().add(attribute.getValue());
            }
        }

        return multichoiceQuestion;
    }

    @Override
    public List<LearningAttribute> convertToLearningAttribute(MultiChoiceQuestion learning) {
        List<LearningAttribute> attributes = new ArrayList<>();

        LearningAttribute question = new LearningAttribute();
        question.setLearningId(learning.getId());
        question.setAttribute("question");
        question.setValue(learning.getQuestion());
        attributes.add(question);

        for (String choice : learning.getListChoice()){
            LearningAttribute choiceAttr = new LearningAttribute();
            choiceAttr.setLearningId(learning.getId());
            choiceAttr.setAttribute("choice");
            choiceAttr.setValue(choice);
            attributes.add(choiceAttr);
        }

        for (String correctChoice : learning.getCorrectChoice()){
            LearningAttribute choiceAttr = new LearningAttribute();
            choiceAttr.setLearningId(learning.getId());
            choiceAttr.setAttribute("correct");
            choiceAttr.setValue(correctChoice);
            attributes.add(choiceAttr);
        }

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

package com.happiness.membread.contexts.study.application.controllers;

import com.happiness.membread.common.ApiResponse;
import com.happiness.membread.contexts.study.database.entities.Learning;
import com.happiness.membread.contexts.study.database.entities.LearningAttribute;
import com.happiness.membread.contexts.study.database.repositories.LearningAttributeRepository;
import com.happiness.membread.contexts.study.database.repositories.LearningRepository;
import com.happiness.membread.contexts.study.domain.common.ConvertLearning;
import com.happiness.membread.contexts.study.domain.common.ILearning;
import com.happiness.membread.contexts.study.domain.aggregates.vocabularylesson.learnings.ConvertVocabularyStrategy;
import com.happiness.membread.contexts.study.domain.aggregates.vocabularylesson.learnings.Vocabulary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lesson")
@Slf4j
public class LessonController {
    @Autowired
    private LearningAttributeRepository learningAttributeRepository;

    @Autowired
    private LearningRepository learningRepository;

    @Autowired
    private ConvertLearning convertLearning;

    /**
     * Get lesson detail. If user hasn't enrolled course yet, user can't see lesson detail.
     */
    public Object getLesson(String id){
        return null;
    }

    /**
     * Create new lesson, only for Author or Staff of course.
     * @return "Success" if created successfully lesson and "Failed" if not
     */
    public Object createLesson(){
        return null;
    }

    /**
     * Update lesson, only for Author or Staff of course.
     */
    public Object updateLesson(){
        return null;
    }

    @PostMapping("")
    public ApiResponse<String> createNewVocabulary(@RequestBody Vocabulary vocabulary){
        Learning learning = new Learning();
        learning.setType("vocabulary");
        List<ILearning> listLearning = new ArrayList<>();
        listLearning.add(vocabulary);
        learningRepository.save(learning);
        System.out.println("LEARNING ID : "+learning.getId());
        vocabulary.setLearningId(learning.getId());

        List<LearningAttribute> learningList = convertLearning.convertToListLearning(ConvertVocabularyStrategy.class.getName(), listLearning);
        learningList.forEach(e->{
            log.info(e.getAttribute());
            log.info(e.getValue());
            log.info(e.getLearningId());
        });
        learningAttributeRepository.saveAll(learningList);

        ApiResponse<String> response = new ApiResponse<>();
        response.setCode(1000);
        response.setMessage("Success");

        return response;
    }
}

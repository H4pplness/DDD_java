package com.happiness.membread.contexts.study.application.controllers;

import com.happiness.membread.common.ApiResponse;
import com.happiness.membread.contexts.study.database.entities.Learning;
import com.happiness.membread.contexts.study.database.entities.LearningAttribute;
import com.happiness.membread.contexts.study.database.entities.Lesson;
import com.happiness.membread.contexts.study.database.repositories.LearningAttributeRepository;
import com.happiness.membread.contexts.study.database.repositories.LearningRepository;
import com.happiness.membread.contexts.study.database.repositories.LessonRepository;
import com.happiness.membread.contexts.study.domain.aggregates.learningelements.ConvertLearning;
import com.happiness.membread.contexts.study.domain.aggregates.learningelements.ILearning;
import com.happiness.membread.contexts.study.domain.aggregates.learningelements.vocabulary.ConvertVocabularyStrategy;
import com.happiness.membread.contexts.study.domain.aggregates.learningelements.vocabulary.Vocabulary;
import com.happiness.membread.contexts.study.domain.dtos.CreateLessonDto;
import com.happiness.membread.contexts.study.domain.services.lessonservices.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private LessonService lessonService;

    /**
     * Get lesson detail. If user hasn't enrolled course yet, user can't see lesson detail.
     */
    @GetMapping("")
    public ApiResponse<String> getLesson(@PathVariable String id){


        ApiResponse<String> response = new ApiResponse<>();
        response.setCode(1000);
        response.setMessage("Success");
        return response;
    }

    /**
     * Create new lesson, only for Author or Staff of course.
     * @return "Success" if created successfully lesson and "Failed" if not
     */
    @PostMapping("")
    public ApiResponse<String> createLesson(@RequestBody CreateLessonDto createLesson){
        lessonService.createLesson(createLesson);

        ApiResponse<String> response = new ApiResponse<>();
        response.setCode(1000);
        response.setMessage("Success");
        return response;
    }

    /**
     * Update lesson, only for Author or Staff of course.
     */
    public Object updateLesson(){
        return null;
    }

    @PostMapping("/vocab")
    public ApiResponse<String> createNewVocabulary(@RequestBody Vocabulary vocabulary){
        Learning learning = new Learning();
        learning.setType("vocabulary");
        List<ILearning> listLearning = new ArrayList<>();
        listLearning.add(vocabulary);
        learningRepository.save(learning);
        System.out.println("LEARNING ID : "+learning.getId());
        vocabulary.setLearningId(learning.getId());

        List<LearningAttribute> learningList = convertLearning.convertToListLearning(ConvertVocabularyStrategy.class.getName(), listLearning);
        learningAttributeRepository.saveAll(learningList);

        ApiResponse<String> response = new ApiResponse<>();
        response.setCode(1000);
        response.setMessage("Success");
        return response;
    }


}

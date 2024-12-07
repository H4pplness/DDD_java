package com.happiness.membread.contexts.study.application.apis.v1;

import com.happiness.membread.common.ApiResponse;
import com.happiness.membread.contexts.study.database.entities.Clazz;
import com.happiness.membread.contexts.study.database.entities.Learning;
import com.happiness.membread.contexts.study.database.entities.LearningAttribute;
import com.happiness.membread.contexts.study.database.repositories.LearningAttributeRepository;
import com.happiness.membread.contexts.study.database.repositories.LearningRepository;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.LearningConversion;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.vocabulary.Vocabulary;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.vocabulary.VocabularyConversion;
import com.happiness.membread.contexts.study.domain.dtos.CreateClazzRequestDto;
import com.happiness.membread.contexts.study.domain.dtos.LessonSummaryResponseDto;
import com.happiness.membread.contexts.study.domain.service.ClazzService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clazz")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class ClazzController {
    LearningRepository learningRepository;

    LearningAttributeRepository learningAttributeRepository;

    LearningConversion learningConversion;

    ClazzService clazzService;

    @PostMapping("/vocab")
    public ApiResponse<Vocabulary> createVocabulary(@RequestBody Vocabulary vocabulary){
        Learning learning = new Learning();
        learning.setType("vocabulary");
        learningRepository.save(learning);

        vocabulary.setId(learning.getId());
        List<LearningAttribute> attributes = learningConversion.convertToLearningAttributes(VocabularyConversion.class.getName(),vocabulary);

        learningAttributeRepository.saveAll(attributes);

        return ApiResponse.<Vocabulary>builder().result(vocabulary).build();
    }

    @PostMapping("")
    public ApiResponse<Clazz> createClazz(@Valid @RequestBody CreateClazzRequestDto createClazzDto){
        return ApiResponse.<Clazz>builder().result(clazzService.createClazz(createClazzDto)).build();
    }

    @GetMapping("")
    public ApiResponse<List<LessonSummaryResponseDto>> getLessonOfClazz(@PathParam("id") String id){
        return ApiResponse.<List<LessonSummaryResponseDto>>builder().result(clazzService.getLessonOfClazz(id)).build();
    }

}

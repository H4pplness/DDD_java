package com.happiness.membread.contexts.study.application.apis.v1;

import com.happiness.membread.common.ApiResponse;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.Learning;
import com.happiness.membread.contexts.study.domain.dtos.AddLearningToNewLessonRequestDto;
import com.happiness.membread.contexts.study.domain.dtos.CreateLessonRequestDto;
import com.happiness.membread.contexts.study.domain.dtos.LessonSummaryResponseDto;
import com.happiness.membread.contexts.study.domain.service.LessonService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/api/v1/lesson")
@Slf4j
public class LessonController {
    LessonService lessonService;

    @PostMapping("/new")
    public ApiResponse<LessonSummaryResponseDto> createLesson(@Valid @RequestBody CreateLessonRequestDto newLesson){
        return ApiResponse.<LessonSummaryResponseDto>builder().result(lessonService.createLesson(newLesson)).build();
    }

    @PostMapping("/add-learning")
    public ApiResponse<List<Learning>> addLearningToNewLesson(@RequestBody AddLearningToNewLessonRequestDto lesson){
        List<Learning> list = lessonService.addLearning(lesson.getId(),lesson.getList());

        return ApiResponse.<List<Learning>>builder().result(list).build();
    }
}

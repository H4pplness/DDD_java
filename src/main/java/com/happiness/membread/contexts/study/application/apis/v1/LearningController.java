package com.happiness.membread.contexts.study.application.apis.v1;

import com.happiness.membread.common.ApiResponse;
import com.happiness.membread.contexts.study.domain.aggregates.learningmethods.LearningLesson;
import com.happiness.membread.contexts.study.domain.aggregates.userprogress.LessonProgress;
import com.happiness.membread.contexts.study.domain.dtos.UpdateLessonProgressRequestDto;
import com.happiness.membread.contexts.study.domain.service.LearningService;
import jakarta.websocket.server.PathParam;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/learning")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class LearningController {
    LearningService learningService;

    @GetMapping("/lesson")
    public ApiResponse<LessonProgress> getLessonProgress(@PathParam("id") String id){
        return ApiResponse.<LessonProgress>builder().result(learningService.getLessonProgress(id,"1111")).build();
    }

    @GetMapping("/clazz")
    public ApiResponse<String> getClazzProgress(@PathParam("id") String id){
        return ApiResponse.<String>builder().result("80%").build();
    }

    @PostMapping("/update-progress")
    public ApiResponse<String> updateProgress(@RequestBody UpdateLessonProgressRequestDto progressRequestDto){
        learningService.updateLessonProgress("1111",progressRequestDto);

        return ApiResponse.<String>builder().result("Updated successfully!").build();
    }

    @GetMapping("/study")
    public ApiResponse<LearningLesson> studyLesson(@PathParam("lessonId") String lessonId,@PathParam("method") String method){
        String userId = "1111";
        return ApiResponse.<LearningLesson>builder().result(learningService.studyLesson(lessonId,userId,method)).build();
    }
}

package com.happiness.membread.contexts.study.application.apis.v1;

import com.happiness.membread.common.ApiResponse;
import com.happiness.membread.contexts.study.domain.dtos.AddCategoriesToCourseRequestDto;
import com.happiness.membread.contexts.study.domain.dtos.CreateCourseRequestDto;
import com.happiness.membread.contexts.study.domain.service.CourseService;
import jakarta.websocket.server.PathParam;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class CourseController {
    CourseService courseService;

    @GetMapping("")
    public ApiResponse<Object> getCourseInfo(@PathParam("id") String id){
        return ApiResponse.builder().result(courseService.getCourseInfo(id)).build();
    }

    @PostMapping("")
    public ApiResponse<Object> createCourse(@RequestBody CreateCourseRequestDto course){
        return ApiResponse.<Object>builder().result(courseService.createCourse(course)).build();
    }

    @PutMapping("")
    public ApiResponse<Object> updateCourseInfo(){
        return ApiResponse.<Object>builder().result(courseService.updateCourseInfo()).build();
    }

    @PostMapping("add-category")
    public ApiResponse<Object> addCategory(@RequestBody AddCategoriesToCourseRequestDto categories){
        courseService.addCategory(categories.getCourseId(), categories.getCategories());

        return ApiResponse.builder().result("Successful !").build();
    }

    @GetMapping("search")
    public ApiResponse<Object> getCourseByCategory(@PathParam("categoryId") Integer categoryId){
        return ApiResponse.builder().result(courseService.getCourseByCategory(categoryId)).build();
    }
}

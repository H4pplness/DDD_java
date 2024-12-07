package com.happiness.membread.contexts.study.application.apis.v1;

import com.happiness.membread.common.ApiResponse;
import com.happiness.membread.contexts.study.domain.dtos.CreateCategoryRequestDto;
import com.happiness.membread.contexts.study.domain.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class CategoryController {
    CategoryService categoryService;

    @PostMapping("")
    public ApiResponse<String> createNewCategory(@RequestBody CreateCategoryRequestDto category){
        categoryService.createCategory(category);
        return ApiResponse.<String>builder().result("Create successful !").build();
    }
}

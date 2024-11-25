package com.happiness.membread.contexts.study.application.controllers;

import com.happiness.membread.common.ApiResponse;
import com.happiness.membread.contexts.study.database.entities.Clazz;
import com.happiness.membread.contexts.study.domain.dtos.CreateClazzDto;
import com.happiness.membread.contexts.study.domain.services.ClazzService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/clazz")
@NoArgsConstructor
@AllArgsConstructor
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
    * Get class what is being studied by user;
    */
    public Object getStudiedClazz(){
        return null;
    }

    /**
     * Get top clazz in community;
     */
    public Object getPopularClazz(){
        return null;
    }

    @GetMapping("")
    public ApiResponse<Clazz> getClazzById(@RequestParam String id){
        Clazz clazz = clazzService.getClazz(id);
        return ApiResponse.<Clazz>builder().result(clazz).build();
    }

    /**
     * For teacher
     */
    @PostMapping("/class")
    public ApiResponse<String> createClass(@Valid @RequestBody CreateClazzDto clazz){
        clazzService.createClazz(clazz);
        return ApiResponse.<String>builder().code(1000).message("Success !").build();
    }


    /**
     * For teacher
     */
    public Object updateClazz(){
        return null;
    }
}

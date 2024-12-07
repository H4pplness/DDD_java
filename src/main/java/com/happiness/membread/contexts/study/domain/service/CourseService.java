package com.happiness.membread.contexts.study.domain.service;

import com.happiness.membread.contexts.study.database.entities.Category;
import com.happiness.membread.contexts.study.database.entities.Course;
import com.happiness.membread.contexts.study.database.repositories.CategoryRepository;
import com.happiness.membread.contexts.study.database.repositories.CourseRepository;
import com.happiness.membread.contexts.study.domain.dtos.CreateCourseRequestDto;
import javassist.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class CourseService {
    CourseRepository courseRepository;

    CategoryService categoryService;

    public Course createCourse(CreateCourseRequestDto request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setAuthorId(userId);

        return courseRepository.save(course);
    }

    public Course getCourseInfo(String id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()){
            throw new RuntimeException("Not found !");
        }
        Set<Category> categories = categoryService.getCategoriesOfCourse(id);

        course.get().setCategories(categories);
        return course.get();
    }

    public Object updateCourseInfo(){
        return null;
    }

    public Object addCategory(String courseId,List<Integer> categories){
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isEmpty()){
            throw new RuntimeException("Not found !");
        }

        Course course = optionalCourse.get();
        Set<Category> categorySet;
        if (course.getCategories() == null){
            categorySet = new HashSet<>();
        }else{
            categorySet = course.getCategories();
        }

        for(int categoryId : categories){
            Category category = categoryService.getCategory(categoryId);
            categorySet.add(category);
            System.out.println("CATEGORY : "+category);
        }

        course.setCategories(categorySet);
        courseRepository.save(course);

        return "OKE";
    }

    public Object getCourseByCategory(Integer categoryId){
        return courseRepository.findCourseWithCategories(categoryId);
    }

}

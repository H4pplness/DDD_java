package com.happiness.membread.contexts.study.database;

import com.happiness.membread.contexts.study.database.entities.*;
import com.happiness.membread.contexts.study.database.repositories.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
@Slf4j
public class SampleData {
    @Value("${data.sample:false}")
    private boolean mode;

    @Autowired
    private ClazzRepository clazzRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LearningRepository learningRepository;

    @Autowired
    private LearningProgressRepository learningProgressRepository;

    @Autowired
    private  LearningAttributeRepository learningAttributeRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @PreDestroy
    public void destroy(){
        log.info("CREATED SAMPLE DATA SUCCESSFULLY !");
    }

    @PostConstruct
    public void generate(){
        if (mode){

            // create category
            Category category = new Category();
            category.setName("English");
            category.setDescription("This is english !?");
            categoryRepository.save(category);

            Set<Category> categories = new HashSet<>();
            categories.add(category);


            // create course
            Course course = new Course();
            course.setAuthorId("1");
            course.setTitle("English 1");
            course.setCategories(categories);
            course.setDescription("English basic for everyone");
            courseRepository.save(course);


            // create clazz
            Clazz clazz = new Clazz();
            clazz.setCourseId(course.getId());
            clazz.setTitle("Environment");
            clazz.setDescription("To provide environmental vocabularies");
            clazzRepository.save(clazz);

            // create lesson
            Lesson lesson = new Lesson();
            lesson.setName("Part 1: Basic vocabularies");
            lesson.setDescription("To provide some vocabularies");
            lesson.setType("vocabulary");
            lesson.setClazzId(clazz.getId());
            lessonRepository.save(lesson);

            // create vocabulary
            String[] vocabulary = {"Environment","Air pollution","Animal habitat","Emission","Litter"};
            String[] meaning = {"(n) Môi trường","(n) Ô nhiễm không khí","(n) Môi trường sống","(n) Khí thải","(n) Rác thải"};
            List<Learning> learnings = new ArrayList<>();
            for (int i=0;i<5;i++){
                Learning learning = new Learning();
                learning.setType("vocabulary");
                learning.setLessonId(lesson.getId());
                learningRepository.save(learning);
                learnings.add(learning);

                LearningAttribute word= new LearningAttribute();
                word.setAttribute("vocabulary");
                word.setValue(vocabulary[i]);
                word.setLearningId(learning.getId());

                LearningAttribute mean = new LearningAttribute();
                mean.setAttribute("meaning");
                mean.setValue(meaning[i]);
                mean.setLearningId(learning.getId());

                learningAttributeRepository.saveAll(List.of(word,mean));
            }

            // create progress
            for (Learning learning : learnings){
                LearningProgress learningProgress = new LearningProgress();
                learningProgress.setLearningId(learning.getId());
                learningProgress.setProgress(3);
                learningProgress.setLastTime(LocalDateTime.now());
                learningProgress.setUserId("1");
                learningProgressRepository.save(learningProgress);
            }

            destroy();
        }
    }
}

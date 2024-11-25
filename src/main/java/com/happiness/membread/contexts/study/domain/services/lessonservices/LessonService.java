package com.happiness.membread.contexts.study.domain.services.lessonservices;

import com.happiness.membread.common.ApiResponse;
import com.happiness.membread.contexts.study.database.entities.Lesson;
import com.happiness.membread.contexts.study.database.repositories.LearningAttributeRepository;
import com.happiness.membread.contexts.study.database.repositories.LearningRepository;
import com.happiness.membread.contexts.study.database.repositories.LessonRepository;
import com.happiness.membread.contexts.study.domain.dtos.CreateLessonDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class LessonService {
    LessonRepository lessonRepository;

    LearningRepository learningRepository;

    LearningAttributeRepository learningAttributeRepository;

    public void createLesson(CreateLessonDto newlesson){
        Lesson lesson = new Lesson();
        lesson.setName(newlesson.getTitle());
        lesson.setDescription(newlesson.getDescription());
        lesson.setCreatedAt(LocalDateTime.now());
        lesson.setType(newlesson.getType());

        lessonRepository.save(lesson);
    }
}

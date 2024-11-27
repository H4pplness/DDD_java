package com.happiness.membread.contexts.study.domain.service;

import com.happiness.membread.contexts.study.database.entities.Lesson;
import com.happiness.membread.contexts.study.database.repositories.LessonRepository;
import com.happiness.membread.contexts.study.domain.aggregates.learnings.Learning;
import com.happiness.membread.contexts.study.domain.aggregates.lessons.LessonFactory;
import com.happiness.membread.contexts.study.domain.dtos.CreateLessonRequestDto;
import com.happiness.membread.contexts.study.domain.dtos.LessonSummaryResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class LessonService {
    LessonRepository lessonRepository;

    LessonFactory lessonFactory;

    public LessonSummaryResponseDto createLesson(CreateLessonRequestDto newLesson){
        Lesson lesson = new Lesson();
        lesson.setName(newLesson.getName());
        lesson.setCreatedAt(LocalDateTime.now());
        lesson.setType(newLesson.getType());
        lesson.setClazzId(newLesson.getClazz_id());
        lesson.setDescription(newLesson.getDescription());

        lessonRepository.save(lesson);

        return LessonSummaryResponseDto.builder().id(lesson.getId()).name(lesson.getName()).type(lesson.getType()).build();
    }

    public List<Learning> addLearning(String id,List<Learning> lesson){
        return lessonFactory.addLearning(id,lesson);
    }

    public List<Learning> editLearning(String id,List<Learning> lesson){
        return lessonFactory.editLearning(id,lesson);
    }
}

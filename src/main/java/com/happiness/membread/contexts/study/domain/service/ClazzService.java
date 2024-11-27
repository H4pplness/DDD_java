package com.happiness.membread.contexts.study.domain.service;

import com.happiness.membread.contexts.study.database.entities.Clazz;
import com.happiness.membread.contexts.study.database.projections.GetLessonsOfClazzProjection;
import com.happiness.membread.contexts.study.database.repositories.ClazzRepository;
import com.happiness.membread.contexts.study.database.repositories.LessonRepository;
import com.happiness.membread.contexts.study.domain.dtos.CreateClazzRequestDto;
import com.happiness.membread.contexts.study.domain.dtos.LessonSummaryResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ClazzService {
    ClazzRepository clazzRepository;

    LessonRepository lessonRepository;

    public Clazz createClazz(@RequestBody CreateClazzRequestDto createClazz){
        Clazz clazz = new Clazz();
        clazz.setName(createClazz.getName());
        clazz.setDescription(createClazz.getDescription());

        return clazzRepository.save(clazz);
    }

    public List<LessonSummaryResponseDto> getLessonOfClazz(String id){
        List<LessonSummaryResponseDto> listLesson = new ArrayList<>();

        List<GetLessonsOfClazzProjection> lessons = clazzRepository.getLessonsOfClazz(id);
        for (GetLessonsOfClazzProjection lesson : lessons){
            LessonSummaryResponseDto lessonSummary = new LessonSummaryResponseDto();
            lessonSummary.setId(lesson.getId());
            lessonSummary.setName(lesson.getName());
            lessonSummary.setType(lesson.getType());
            listLesson.add(lessonSummary);
        }

        return listLesson;
    }
}

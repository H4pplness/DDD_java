package com.happiness.membread.contexts.study.domain.dtos;

import com.happiness.membread.contexts.study.database.entities.Category;
import com.happiness.membread.contexts.study.database.entities.Clazz;
import com.happiness.membread.contexts.study.database.entities.Course;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseInfoResponse {
    String id;
    String title;
    String description;
    String authorId;
    List<Category> categories;
    List<Clazz> classes;
}

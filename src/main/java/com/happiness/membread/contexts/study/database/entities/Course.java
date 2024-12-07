package com.happiness.membread.contexts.study.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.ElementType;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column()
    private String title;

    @Column()
    private String description;

    @Column()
    private String authorId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "course_category",
            joinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id",referencedColumnName = "id")
    )
    private Set<Category> categories = new HashSet<>();
}

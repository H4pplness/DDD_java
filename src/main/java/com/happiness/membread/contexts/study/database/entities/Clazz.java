package com.happiness.membread.contexts.study.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column()
    private String name;

    @Column()
    private String description;

    @Column()
    private String authorId;

    @ManyToMany()
    @JoinTable(
            name = "clazz_category",
            joinColumns = @JoinColumn(name = "category_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "clazz_id",referencedColumnName = "id")
    )
    private Set<Category> categories = new HashSet<>();
}

package com.happiness.membread.contexts.study.database.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column()
    private String name;

    @Column()
    private String description;

    @Column(name = "clazz_id")
    private String clazzId;

    @CreatedDate
    @Column(updatable = false,name = "created_at")
    private LocalDateTime createdAt;

    @Column()
    private String type;
}

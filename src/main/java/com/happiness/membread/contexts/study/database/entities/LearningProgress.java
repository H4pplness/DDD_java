package com.happiness.membread.contexts.study.database.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
public class LearningProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "learning_id")
    private String learningId;

    @Column(name = "user_id")
    private String userId;

    @ColumnDefault(value = "0")
    @Column(name = "progress")
    private int progress;

    @Column(name = "next_time")
    private LocalDateTime nextTime;
}

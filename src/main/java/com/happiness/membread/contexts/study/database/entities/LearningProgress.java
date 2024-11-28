package com.happiness.membread.contexts.study.database.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Data
@IdClass(LearningProgressKey.class)
public class LearningProgress {
    @Id
    @Column(name = "learning_id")
    private String learningId;

    @Id
    @Column(name = "user_id")
    private String userId;

    @ColumnDefault(value = "0")
    @Column(name = "progress")
    private int progress;

    @Column(name = "last_time")
    private LocalDateTime lastTime;
}

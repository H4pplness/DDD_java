package com.happiness.membread.contexts.study.domain.dtos;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClazzDto {
    @Size(min = 6,message = "Name at least 6 characters !")
    private String name;
    private String description;
}

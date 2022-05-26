package com.bezkoder.spring.jpa.postgresql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TutorialDTO {
    private Long id;
    private String title;
    private String description;
    private boolean published;
}



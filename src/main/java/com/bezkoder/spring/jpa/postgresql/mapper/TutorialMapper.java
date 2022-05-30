package com.bezkoder.spring.jpa.postgresql.mapper;

import com.bezkoder.spring.jpa.postgresql.dto.TutorialDTO;
import com.bezkoder.spring.jpa.postgresql.model.Tutorial;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TutorialMapper {
    TutorialMapper INSTANCE = Mappers.getMapper(TutorialMapper.class);

    TutorialDTO toDTO(Tutorial tutorial);

    Tutorial toEntity(TutorialDTO tutorialDTO);
}

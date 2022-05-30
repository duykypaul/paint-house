package com.bezkoder.spring.jpa.postgresql.mapper;

import com.bezkoder.spring.jpa.postgresql.dto.TutorialDTO;
import com.bezkoder.spring.jpa.postgresql.model.Tutorial;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TutorialMapper {
    TutorialMapper INSTANCE = Mappers.getMapper(TutorialMapper.class);
    @InheritConfiguration
    TutorialDTO toDTO(Tutorial tutorial);

    @InheritConfiguration
    Tutorial toEntity(TutorialDTO tutorialDTO);
}

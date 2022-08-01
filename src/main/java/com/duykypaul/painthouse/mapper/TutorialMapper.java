package com.duykypaul.painthouse.mapper;

import com.duykypaul.painthouse.dto.TutorialDTO;
import com.duykypaul.painthouse.model.Tutorial;
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

package com.duykypaul.painthouse.mapper;

import com.duykypaul.painthouse.dto.CategoryDTO;
import com.duykypaul.painthouse.model.Category;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @InheritConfiguration
    CategoryDTO toDTO(Category category);

    @InheritConfiguration
    Category toEntity(CategoryDTO categoryDTO);
}

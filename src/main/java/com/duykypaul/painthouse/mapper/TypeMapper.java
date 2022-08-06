package com.duykypaul.painthouse.mapper;

import com.duykypaul.painthouse.dto.CategoryDTO;
import com.duykypaul.painthouse.dto.ProductDTO;
import com.duykypaul.painthouse.model.Category;
import com.duykypaul.painthouse.model.Product;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @InheritConfiguration
    ProductDTO toDTO(Product element);

    @InheritConfiguration
    Product toEntity(ProductDTO element);
}

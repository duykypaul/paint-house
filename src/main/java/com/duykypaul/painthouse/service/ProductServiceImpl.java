package com.duykypaul.painthouse.service;

import com.duykypaul.painthouse.dto.CategoryDTO;
import com.duykypaul.painthouse.mapper.CategoryMapper;
import com.duykypaul.painthouse.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Long, ProductDTO> {

    protected ProductServiceImpl(JpaRepository<Product, Long> repository) {
        super(repository);
    }

    @Override
    public Product toEntity(ProductDTO element) {
        return ProductMapper.INSTANCE.toEntity(element);
    }

    @Override
    public ProductDTO toDTO(Product element) {
        return ProductMapper.INSTANCE.toDTO(element);
    }
}

package com.duykypaul.painthouse.service;

import com.duykypaul.painthouse.dto.CategoryDTO;
import com.duykypaul.painthouse.mapper.CategoryMapper;
import com.duykypaul.painthouse.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, Long, CategoryDTO> {

    protected CategoryServiceImpl(JpaRepository<Category, Long> repository) {
        super(repository);
    }

    @Override
    public Category toEntity(CategoryDTO element) {
        return CategoryMapper.INSTANCE.toEntity(element);
    }

    @Override
    public CategoryDTO toDTO(Category element) {
        return CategoryMapper.INSTANCE.toDTO(element);
    }
}

package com.duykypaul.painthouse.service;

import com.duykypaul.painthouse.dto.ProductDTO;
import com.duykypaul.painthouse.mapper.ProductMapper;
import com.duykypaul.painthouse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Long, ProductDTO> implements ProductService {

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

package com.duykypaul.painthouse.api;

import com.duykypaul.painthouse.dto.ProductDTO;
import com.duykypaul.painthouse.model.Product;
import com.duykypaul.painthouse.service.CommonService;
import com.duykypaul.painthouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
public class ProductController {
    final ProductService productService;
    final CommonService commonService;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public ProductDTO saveOrUpdate(@RequestBody ProductDTO dto) {
        return productService.saveOrUpdate(dto);
    }

    @PutMapping
    public Long updateIgnoreNull(@RequestBody ProductDTO dto) {
        return productService.updateIgnoreNull(dto, dto.getId());
    }

    @GetMapping
    public Page<ProductDTO> findPaging(@RequestParam(required = false) Long categoryId,
                                       @RequestParam(required = false, defaultValue = "1") Integer page,
                                       @RequestParam(required = false, defaultValue = "10") Integer size,
                                       @RequestParam(defaultValue = "id") String sortBy) {
        Pageable paging = PageRequest.of(page - 1, size, Sort.by(sortBy));
        if (ObjectUtils.isEmpty(categoryId)) {
            return productService.findAll(paging);
        }
        Example<Product> dExample = Example.of(Product.builder().categoryId(categoryId).build());
        return productService.findByExample(dExample, paging);
    }
}

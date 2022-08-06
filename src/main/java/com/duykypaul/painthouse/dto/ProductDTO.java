package com.duykypaul.painthouse.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Boolean deleted;

    private Long categoryId;

    private String name;

    private String description;

    private Long typeId;

}

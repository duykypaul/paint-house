package com.duykypaul.painthouse.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long categoryId;

    private String name;

    private String description;

    private String metaCode;

    private String metaType;

    private String packing;
}

package com.duykypaul.painthouse.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product", indexes = @Index(name = "product_category_index", columnList = "category_id"))
public class Product extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "meta_code")
    private String metaCode;

    @Column(name = "meta_type")
    private String metaType;

    @Column(name = "packing")
    private String packing;

}

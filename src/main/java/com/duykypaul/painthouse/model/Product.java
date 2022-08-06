package com.duykypaul.painthouse.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "product")
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "is_deleted", nullable = false)
    private Boolean deleted;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type_id")
    private Long typeId;

}

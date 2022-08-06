package com.duykypaul.painthouse.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "public.type")
@EqualsAndHashCode(callSuper = true)
public class Type extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "package", nullable = false)
    private String packageField;

    @Column(name = "is_deleted", nullable = false)
    private Boolean deleted;

}

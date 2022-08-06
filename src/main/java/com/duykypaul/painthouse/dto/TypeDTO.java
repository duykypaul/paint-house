package com.duykypaul.painthouse.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class TypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;

    private String name;

    private String packageField;

    private Boolean deleted;

}

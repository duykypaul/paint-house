package com.bezkoder.spring.jpa.postgresql.dto;

import com.bezkoder.spring.jpa.postgresql.common.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@SuperBuilder
@NoArgsConstructor
public class BaseDTO {

    private Long id;

    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern=Constant.FORMAT_DATE.DATE_TIME_DEFAULT)
    private LocalDate createdAt;

    private String modifiedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern=Constant.FORMAT_DATE.DATE_TIME_DEFAULT)
    private LocalDate modifiedAt;

    private boolean isDeleted;
}



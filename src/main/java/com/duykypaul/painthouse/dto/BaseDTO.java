package com.duykypaul.painthouse.dto;

import com.duykypaul.painthouse.common.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Data
@SuperBuilder
@NoArgsConstructor
public class BaseDTO {

    private Long id;

    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.FormatDate.DATE_TIME_DEFAULT)
    private LocalDateTime createdAt;

    private String modifiedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.FormatDate.DATE_TIME_DEFAULT)
    private LocalDateTime modifiedAt;

    private Boolean deleteFlag;
}



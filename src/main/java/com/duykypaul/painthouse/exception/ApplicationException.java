package com.duykypaul.painthouse.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApplicationException extends RuntimeException {

    /**
     *
     */

    private static final long serialVersionUID = 1L;
    private String errorCode;

}

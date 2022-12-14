package com.duykypaul.painthouse.exception;

import com.duykypaul.painthouse.common.MessageUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerNotFoundException(ApplicationException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerNotFoundException(MethodArgumentTypeMismatchException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, "request không hợp lệ");
    }

    @ExceptionHandler({ExpiredJwtException.class,})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handlerNotFoundException() {
        return new ErrorResponse(MessageUtils.getMessage("user.login.expire"));
    }

    @ExceptionHandler({BadCredentialsException.class,})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handlerLoginFailException() {
        return new ErrorResponse(MessageUtils.getMessage("user.login.invalid"));
    }
}

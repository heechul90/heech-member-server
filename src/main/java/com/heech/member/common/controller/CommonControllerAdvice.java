package com.heech.member.common.controller;

import com.heech.member.common.exception.CommonException;
import com.heech.member.common.json.JsonError;
import com.heech.member.common.json.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CommonControllerAdvice {

    //bindingResult erorrs
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<JsonError> errors = e.getFieldErrors().stream()
                .map(error -> new JsonError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return JsonResult.ERROR(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), errors);
    }

    //custom errors
    @ExceptionHandler(CommonException.class)
    public JsonResult commonExceptionHandler(CommonException e) {
        return JsonResult.ERROR(e.httpStatus(), e.getMessage(), e.getErrors());
    }
}

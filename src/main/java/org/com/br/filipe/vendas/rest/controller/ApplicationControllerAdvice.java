package org.com.br.filipe.vendas.rest.controller;

import org.com.br.filipe.vendas.exception.RegraNegocioException;
import org.com.br.filipe.vendas.rest.ApiErrors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@ControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException regraNegocioException) {
        return new ApiErrors(regraNegocioException.getMessage());
    }

}

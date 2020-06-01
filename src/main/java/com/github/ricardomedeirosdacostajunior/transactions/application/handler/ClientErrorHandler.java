package com.github.ricardomedeirosdacostajunior.transactions.application.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.github.ricardomedeirosdacostajunior.transactions.domain.exception.ClientErrorException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClientErrorHandler {

  @ExceptionHandler(ClientErrorException.class)
  @ResponseStatus(BAD_REQUEST)
  public String clientErrorException(final ClientErrorException clientErrorException) {
    return clientErrorException.getMessage();
  }
}

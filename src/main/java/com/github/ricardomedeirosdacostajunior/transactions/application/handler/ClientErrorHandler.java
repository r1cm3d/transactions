package com.github.ricardomedeirosdacostajunior.transactions.application.handler;

import com.github.ricardomedeirosdacostajunior.transactions.domain.exception.ClientErrorException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClientErrorHandler {

  @ExceptionHandler(ClientErrorException.class)
  public String clientErrorException(final ClientErrorException clientErrorException) {
    return clientErrorException.getMessage();
  }
}

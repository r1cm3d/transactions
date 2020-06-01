package com.github.ricardomedeirosdacostajunior.transactions.domain.exception;

public class ClientErrorException extends RuntimeException {

  protected ClientErrorException(final String message) {
    super(message);
  }
}

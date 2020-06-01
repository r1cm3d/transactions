package com.github.ricardomedeirosdacostajunior.transactions.domain.exception;

public final class InvalidOperationTypeException extends ClientErrorException {

  public InvalidOperationTypeException() {
    super("Operation type is invalid");
  }
}

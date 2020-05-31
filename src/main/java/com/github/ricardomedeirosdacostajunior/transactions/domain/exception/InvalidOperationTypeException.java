package com.github.ricardomedeirosdacostajunior.transactions.domain.exception;

public class InvalidOperationTypeException extends RuntimeException {

  public InvalidOperationTypeException() {
    super("Operation type is invalid");
  }
}

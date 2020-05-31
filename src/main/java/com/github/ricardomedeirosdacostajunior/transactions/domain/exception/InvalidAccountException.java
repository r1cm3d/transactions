package com.github.ricardomedeirosdacostajunior.transactions.domain.exception;

public class InvalidAccountException extends RuntimeException {

  public InvalidAccountException() {
    super("Account invalid or not found");
  }
}

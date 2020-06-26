package com.github.ricardomedeirosdacostajunior.transactions.domain.exception;

public class InsufficientAvailableCreditLimitException extends ClientErrorException {

  public InsufficientAvailableCreditLimitException() {
    super("Insufficient available credit limit for this operation");
  }
}

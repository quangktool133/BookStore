package com.bookstore.validator.handler;

public interface IValidationHandler<T> {

    String handle(T t, Object dataInvoked);
}



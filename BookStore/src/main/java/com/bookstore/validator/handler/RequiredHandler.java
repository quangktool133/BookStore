package com.bookstore.validator.handler;

import com.bookstore.validator.annotation.Required;

import java.util.Objects;

public class RequiredHandler implements IValidationHandler<Required> {

    @Override
    public String handle(Required required, Object dataInvoked) {
        if (Objects.isNull(dataInvoked) || dataInvoked.toString().trim().isEmpty()) {
            return required.message();
        }
        return null;
    }
}
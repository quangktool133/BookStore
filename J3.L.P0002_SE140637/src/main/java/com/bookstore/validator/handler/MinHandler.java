package com.bookstore.validator.handler;

import com.bookstore.util.DataUtil;
import com.bookstore.validator.annotation.Min;

public class MinHandler implements IValidationHandler<Min> {

    @Override
    public String handle(Min min, Object dataInvoked) {
        double i = DataUtil.safeToDouble(dataInvoked);
        if (min.value() > i) {
            return min.message();
        }
        return null;
    }
}
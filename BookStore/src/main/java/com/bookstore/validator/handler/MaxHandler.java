package com.bookstore.validator.handler;

import com.bookstore.util.DataUtil;
import com.bookstore.validator.annotation.Max;

public class MaxHandler implements IValidationHandler<Max> {

    @Override
    public String handle(Max max, Object dataInvoked) {
        double i = DataUtil.safeToDouble(dataInvoked);
        if (max.value() < i) {
            return max.message();
        }
        return null;
    }
}
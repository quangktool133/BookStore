package com.bookstore.validator.handler;

import com.bookstore.util.DataUtil;
import com.bookstore.validator.annotation.Range;

public class RangeHandler implements IValidationHandler<Range> {

    @Override
    public String handle(Range range, Object dataInvoked) {
        double i = DataUtil.safeToDouble(dataInvoked);
        if (i < range.min() || i > range.max()) {
            return range.message();
        }
        return null;
    }
}
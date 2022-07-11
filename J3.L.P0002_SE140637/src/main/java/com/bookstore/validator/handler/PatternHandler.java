package com.bookstore.validator.handler;

import com.bookstore.util.DataUtil;
import com.bookstore.validator.annotation.Pattern;

public class PatternHandler implements IValidationHandler<Pattern> {

    @Override
    public String handle(Pattern pattern, Object dataInvoked) {
        if (!DataUtil.safeToString(dataInvoked).trim().matches(pattern.value())) {
            return pattern.message();
        }
        return null;
    }
}
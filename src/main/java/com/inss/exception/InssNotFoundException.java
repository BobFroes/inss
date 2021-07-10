package com.inss.exception;

import com.inss.common.constant.ErrorMessage;

public class InssNotFoundException extends RuntimeException {
    public InssNotFoundException() {
        super(ErrorMessage.INSS_NOT_FOUND);
    }
}
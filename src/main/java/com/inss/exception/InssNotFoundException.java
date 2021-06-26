package com.inss.exception;

import com.inss.common.constant.ErrorMessageConstant;

public class InssNotFoundException extends RuntimeException {
    public InssNotFoundException() {
        super(ErrorMessageConstant.INSS_NOT_FOUND);
    }
}
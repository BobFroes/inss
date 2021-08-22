package com.inss.exception;

import com.inss.common.constant.ErrorMessage;

public class NotFoundException extends RuntimeException {
    public NotFoundException() { super(ErrorMessage.NOT_FOUND); }
}
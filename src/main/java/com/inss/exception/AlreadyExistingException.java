package com.inss.exception;

import com.inss.common.constant.ErrorMessage;

public class AlreadyExistingException extends RuntimeException {
    public AlreadyExistingException() { super(ErrorMessage.ALREADY_EXISTING); }
}
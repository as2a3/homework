package com.said.homework.base.data.exception;

import com.said.homework.base.domain.exception.AppException;

public class DatabaseException extends AppException {

    public DatabaseException(Throwable throwable) {
        super(throwable);
    }
}
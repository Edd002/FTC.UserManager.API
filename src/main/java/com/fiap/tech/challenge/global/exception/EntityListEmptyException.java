package com.fiap.tech.challenge.global.exception;

import com.fiap.tech.challenge.global.base.BaseErrorResponse;
import com.fiap.tech.challenge.global.base.response.error.BaseErrorResponse404;

import java.io.Serial;
import java.util.List;

public final class EntityListEmptyException extends ApiException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EntityListEmptyException(String message) {
        super(message);
    }

    @Override
    public BaseErrorResponse getBaseErrorResponse() {
        return new BaseErrorResponse404(List.of(super.getMessage()));
    }
}
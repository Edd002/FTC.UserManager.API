package com.fiap.tech.challenge.global.entity.specification;

import com.fiap.tech.challenge.global.entity.enumerated.SearchOperationEnum;
import lombok.Getter;

@Getter
public final class SearchCriteria {

    private final String key;
    private final SearchOperationEnum operation;
    private Object value;
    private Object param;

    public SearchCriteria(final String key, final SearchOperationEnum operation) {
        this.key = key;
        this.operation = operation;
    }

    public SearchCriteria(final String key, final SearchOperationEnum operation, final Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(String key, SearchOperationEnum operation, Object value, Object param) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.param = param;
    }
}
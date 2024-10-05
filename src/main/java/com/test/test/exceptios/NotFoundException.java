package com.test.test.exceptios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotFoundException extends RuntimeException {

    private String code;
    private Object[] messageArguments;

    public NotFoundException(String code) {
        this.code = code;
    }
}

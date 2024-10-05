package com.test.test.exceptios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserException extends RuntimeException {

    private String code;
    private Object[] messageArguments;

    public UserException(String code, String message) {
        super(message);
        this.code = code;
    }

    public UserException(String code, String message, Object[] messageArguments) {
        super(message);
        this.code = code;
        this.messageArguments = messageArguments;
    }
}


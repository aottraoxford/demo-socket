package com.socket.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Error extends RuntimeException {
    private int status;
    public Error(int status, String message) {
        super(message);
        this.status = status;
    }    
}

package com.socket.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SignIn {
    @NotNull
    @NotEmpty
    @NotBlank
    private String phoneNumber;
    @NotNull
    @NotEmpty
    @NotBlank
    private String password;
}

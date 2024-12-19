package ru.itmo.wp.form;

import javax.validation.constraints.*;

public class UserCredentials {
    @NotNull
    @NotEmpty
    @NotBlank(message = "Login can't be empty")
    @Size(min = 2, max = 60, message = "Login length should be between 2 and 16")
    @Pattern(regexp = "[a-z]+", message = "Expected lowercase Latin letters")
    private String login;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 60, message = "Password length should be between 4 and 60")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

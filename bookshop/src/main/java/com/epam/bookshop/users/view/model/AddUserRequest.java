package com.epam.bookshop.users.view.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class AddUserRequest {
    @NotNull
    @Size(min = 2, max = 255)
    private String email;
    @NotNull
    @Size(min = 2, max = 255)
    private String password;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfBirth;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}

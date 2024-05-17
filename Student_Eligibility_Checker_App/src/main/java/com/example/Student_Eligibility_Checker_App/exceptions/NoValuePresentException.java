package com.example.Student_Eligibility_Checker_App.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoValuePresentException extends RuntimeException{
    public NoValuePresentException(String msg){
        super(msg);
    }
}

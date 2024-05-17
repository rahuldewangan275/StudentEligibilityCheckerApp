package com.example.Student_Eligibility_Checker_App.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CSVFileFormatException extends  RuntimeException {
    public CSVFileFormatException(String msg){
        super(msg);
    }
}

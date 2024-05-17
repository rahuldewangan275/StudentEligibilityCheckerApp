package com.example.Student_Eligibility_Checker_App.controller;

import com.example.Student_Eligibility_Checker_App.exceptions.CSVFileFormatException;
import com.example.Student_Eligibility_Checker_App.exceptions.StudentNotFoundException;
import com.example.Student_Eligibility_Checker_App.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // API-1 upload students in form of csv file
    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity uploadStudents(HttpServletResponse servletResponse, @RequestPart("file") MultipartFile file, @RequestParam("science-marks") int science, @RequestParam("maths-marks") int maths, @RequestParam("english-marks") int english, @RequestParam("computer-marks") int computer){
    //check if file is present or not
    if(file==null){
        return new ResponseEntity<>("no file found! please select a file", HttpStatus.BAD_REQUEST);
    }

    // check-> file is csv or not
    // Get the filename
    String filename = StringUtils.cleanPath(file.getOriginalFilename());
    if (!filename.endsWith(".csv")) {
        return new ResponseEntity("wrong file uploaded! file uploaded is not a csv file", HttpStatus.BAD_REQUEST);
    }

    // downloadable file
    servletResponse.setContentType("text/csv");
    servletResponse.addHeader("Content-Disposition","attachment; filename=\"studentsList.csv\"");
try {
    String response = studentService.uploadStudents(servletResponse, file, science, maths, english, computer);
    return new ResponseEntity<>(response,HttpStatus.CREATED);
}
catch (CSVFileFormatException e){
    return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
}
catch(Exception e){
       return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // search student by roll number and case(1). return eligibility if student present
    // case(2). if student not found return "NA"
    @GetMapping("/searchStudent")
    public ResponseEntity searchStudent( @RequestParam("roll-no") String rollNumber){
        try {
            String  response = studentService.searchStudent(rollNumber);
            return new ResponseEntity<>(response,HttpStatus.FOUND);
        }catch(StudentNotFoundException e){
            return new ResponseEntity<>("Student Not Found",HttpStatus.BAD_REQUEST);
        }
    }
}

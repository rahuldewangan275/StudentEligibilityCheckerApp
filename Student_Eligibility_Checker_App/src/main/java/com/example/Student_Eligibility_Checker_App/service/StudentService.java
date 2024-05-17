package com.example.Student_Eligibility_Checker_App.service;

import com.example.Student_Eligibility_Checker_App.exceptions.CSVFileFormatException;
import com.example.Student_Eligibility_Checker_App.exceptions.NoValuePresentException;
import com.example.Student_Eligibility_Checker_App.exceptions.StudentNotFoundException;
import com.example.Student_Eligibility_Checker_App.model.Student;
import com.example.Student_Eligibility_Checker_App.repo.StudentRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Locked;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    // setup logger
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private StudentRepository studentRepository;

    public String uploadStudents(HttpServletResponse servletResponse, MultipartFile file, int science, int maths, int english, int computer){
        // parse CSV file to create a list of `User` objects

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            // create csv bean reader
                CsvToBean<Student> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Student.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withIgnoreEmptyLine(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<Student> students = csvToBean.parse();

                // get student eligibility updated
                List<Student> updatedStudents = setEligibility(students, science, maths, english, computer);

                // save all students to db
                studentRepository.saveAll(updatedStudents);

                // write students to csv
                writeStudentsToCsv(servletResponse.getWriter(), updatedStudents);
                return "csv file uploaded successfully";
        }catch(IOException e){
            logger.info("Error in uploadStudents method IO");
            throw new RuntimeException("Error in uploading file/IO Exception");
        }catch (Exception e){
            logger.info("Error in uploadStudents method CSV FILE format exception");
            throw new CSVFileFormatException("CSV file Colums are not matched! please check your CSV file columns format");
        }

    }

    // updating the eligibility colum according to their marks
    public List<Student> setEligibility(List<Student> students,  int science, int maths, int english, int computer){
        List<Student> updatedStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getScience() > science && student.getMaths() > maths && student.getEnglish() > english && student.getComputer() > computer) {
                student.setEligibility("YES");
            } else {
                student.setEligibility("NO");
            }
            updatedStudents.add(student);
        }
        return updatedStudents;
    }

    // writing updated students in csv
    public void writeStudentsToCsv(Writer writer,List<Student>updatedStudents){
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
csvPrinter.printRecord("roll number", "student name", "science", "maths", "english", "computer", "Eligibility For Scholorship");
for(Student student : updatedStudents){
    csvPrinter.printRecord(student.getRollNumber(),student.getStudentName(),student.getScience(),student.getMaths(),student.getEnglish(),student.getComputer());
}
        }catch (IOException e){
           logger.info("Error in  writeStudentsToCsv Method ");
        }
    }

    // Searching student by his roll number
    public String searchStudent(String rollNumber) {
        try {
            Optional<Student> studentOptional = studentRepository.findById(rollNumber);

            if(studentOptional.isEmpty()){
                throw new StudentNotFoundException("Student Not Found");
            }

            Student student = studentOptional.get();

            String eligibility = student.getEligibility();
            return eligibility;

        }catch(StudentNotFoundException e){
            logger.info("Student Not Found Exception in searchStudent method service class");
             throw new StudentNotFoundException("Student Not Found");
        }
    }
}

package com.example.Student_Eligibility_Checker_App.model;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Student {
    @Id
    @Column(nullable = false)
    @CsvBindByName(column = "roll number")
    private String rollNumber;

    @CsvBindByName(column = "student name")
    private String studentName;

    @CsvBindByName
    private int science;
    @CsvBindByName
    private int maths;
    @CsvBindByName
    private int english;
    @CsvBindByName
    private int computer;
    @CsvBindByName(column = "Eligibility For Scholorship")
    private String eligibility;

    }

package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student badr;
            Student alex;
            badr = new Student(
                    "badreddine",
                    "elhilali",
                    "bde@gmail.com",
                    23,
                    LocalDate.of(2002, Month.JANUARY,7)
            );
            alex = new Student(
                    "alex",
                    "elhilali",
                    "alex@gmail.com",
                    LocalDate.of(2003,Month.FEBRUARY,7)
            );
            studentRepository.saveAll(List.of(badr, alex));
        };
    }
}

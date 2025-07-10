package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();
//        return List.of(
//                new Student(
//                        1L,
//                        "badreddine",
//                        "elhilali",
//                        "eb@gmail.com",
//                        23,
//                        LocalDate.of(2002,9,7)
//                )
//        );
    }

    public static class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException(String message) {
            super(message);
        }
    }


    public Student addStudent(Student student) {
        System.out.println(student);
        Optional<Student> foundStudent = studentRepository.findByEmail(student.getEmail());
        if (foundStudent.isPresent()) {
            System.out.println("Student already exists");
            //throw new RuntimeException("email already exists");
            throw new EmailAlreadyExistsException("Student already exists");
        }
        studentRepository.save(student);
        System.out.println("Student added successfully");
        return student;

    }

    public void deleteStudent(long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            System.out.println("Student with id "+id+" not found");
            throw new RuntimeException("Student with id "+id+" not found");
        }
        studentRepository.deleteById(id);

    }

    @Transactional
    public void updateStudentendpoint(long id, String firstName, String lastName, String email, LocalDate dob) {
        boolean exists = studentRepository.existsById(id);
        Optional<Student> student = studentRepository.findById(id);

        if (!exists) {
            System.out.println("Student with id "+id+" not found");
            throw new RuntimeException("Student with id "+id+" not found");
        }

        if (firstName != null && firstName.length() > 0 && !Objects.equals(firstName, student.get().getFirstName())) {
            student.get().setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(lastName, student.get().getLastName())) {
            student.get().setLastName(lastName);
        }

        if (email != null && email.length() > 0 && !Objects.equals(email, student.get().getEmail())) {
            student.get().setEmail(email);
        }

        if (dob != null && !Objects.equals(email, student.get().getDob())) {
            student.get().setDob(dob);
        }
    }

    public Student getStudentById(long id) {
        Optional<Student> foundStudent = studentRepository.findById(id);
        if (!foundStudent.isPresent()) {
            System.out.println("Student with id "+id+" not found");
            throw new RuntimeException("Student with id "+id+" not found");
        }
        return foundStudent.get();
    }

    @Transactional
    public void updateStudent(Student student) {
        Optional<Student> foundStudent = studentRepository.findById(student.getId());

        Student updatedStudent = foundStudent.get();

        updatedStudent.setFirstName(student.getFirstName());
        updatedStudent.setLastName(student.getLastName());
        updatedStudent.setEmail(student.getEmail());
        updatedStudent.setDob(student.getDob());

        //studentRepository.save(updatedStudent);
        // No need to call save(), because @Transactional + modifying managed entity auto-saves

    }
}

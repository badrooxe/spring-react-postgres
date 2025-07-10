package com.example.demo.global;

import com.example.demo.student.Student;
import com.example.demo.student.StudentService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(StudentService.EmailAlreadyExistsException.class)
    public String handleEmailExists(StudentService.EmailAlreadyExistsException e, Model model) {
        model.addAttribute("error", e.getMessage());
        model.addAttribute("student", new Student());
        return "new_student";
    }
}

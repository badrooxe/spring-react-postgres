package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(path = "student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Show index page when visiting "/"
//    @GetMapping("/")
//    public String index(Model model) {
//        return "index";  // resolves to src/main/resources/templates/index.html
//    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getStudents();
        return ResponseEntity.ok().body(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok().body(student);
    }

//    @GetMapping
//    public String getStudents(Model model) {
//        List<Student> students = studentService.getStudents();
//        model.addAttribute("students", students);
//        return "students";
//    }

    //api endpoint
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student savedStudent = studentService.addStudent(student);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "AddedSuccessfully");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(savedStudent);
    }

//    @PostMapping("/add")
//    public String addStudent(@ModelAttribute Student student, Model model) {
//        try {
//            studentService.addStudent(student);
//            return "redirect:/student"; // redirect after successful add
//        }catch (StudentService.EmailAlreadyExistsException e){
//            model.addAttribute("error", e.getMessage());
//            model.addAttribute("student", student);
//            return "new_student";
//        }
//    }


//    @GetMapping("/add")
//    public String addStudentPage(Model model) {
////        List<Student> students = studentService.getStudents();
////        model.addAttribute("students", students);
//        if(!model.containsAttribute("student")) {
//            model.addAttribute("student", new Student());
//        }
//        return "new_student";
//    }

    //delete endpoint
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudentResponse(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student with id " + id + " deleted successfully");
    }

    //delete action
//    @PostMapping("/delete/{id}")
//    public String deleteStudentAction(@PathVariable Long id) {
//        studentService.deleteStudent(id);
//        return "redirect:/student";  // or the URL of your student list page
//    }

    //update endpoint
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudentResponse(
            @PathVariable Long id,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) LocalDate dob)
    {
        studentService.updateStudentendpoint(id,firstName,lastName,email,dob);
        return ResponseEntity.ok("Student with id " + id + " updated successfully");
    }

//    @GetMapping("/update/{id}")
//    public String updateStudentPage(@PathVariable Long id,Model model) {
//        Student student = studentService.getStudentById(id);
//        model.addAttribute("student", student);
//        return "update_student";
//    }

//    @PostMapping("/update")
//    public String updateStudent(@ModelAttribute Student student) {
//        studentService.updateStudent(student);
//        return "redirect:/student";
//    }
}

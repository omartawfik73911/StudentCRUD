package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("students", repository.findAll());
        model.addAttribute("student", new Student());

        return "index";
    }

    @PostMapping("/save")
    public String save(Student student) {
        repository.save(student);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        Student student = repository.findById(id)
                .orElseThrow();

        model.addAttribute("students", repository.findAll());
        model.addAttribute("student", student);

        return "index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        repository.deleteById(id);

        return "redirect:/";
    }
}
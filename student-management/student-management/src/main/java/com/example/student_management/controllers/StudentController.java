package com.example.student_management.controllers;

import com.example.student_management.entities.Student;
import com.example.student_management.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/students") // Correction : ajout de la barre oblique initiale
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Endpoint pour sauvegarder un étudiant
    @PostMapping("/save")
    public ResponseEntity<Student> save(@Valid @RequestBody Student student) { // Ajout de @Valid pour la validation
        Student savedStudent = studentService.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // Endpoint pour supprimer un étudiant par ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        boolean deleted = studentService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Pas de contenu si suppression réussie
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retourne 404 si l'étudiant n'existe pas
        }
    }

    // Endpoint pour récupérer tous les étudiants
    @GetMapping("/all")
    public ResponseEntity<List<Student>> findAll() {
        List<Student> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK); // 200 OK
    }

    // Endpoint pour compter le nombre total d'étudiants
    @GetMapping("/count")
    public ResponseEntity<Long> countStudent() {
        long count = studentService.countStudents();
        return new ResponseEntity<>(count, HttpStatus.OK); // Retourne le total des étudiants
    }

    // Endpoint pour récupérer le nombre d'étudiants par année
    @GetMapping("/byYear")
    public ResponseEntity<Collection<Object[]>> findByYear() { // Correction pour spécifier le type retourné
        Collection<Object[]> studentsByYear = studentService.findNbrStudentByYear();
        return new ResponseEntity<>(studentsByYear, HttpStatus.OK); // 200 OK
    }
}

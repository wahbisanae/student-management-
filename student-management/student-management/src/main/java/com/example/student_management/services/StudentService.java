package com.example.student_management.services;

import com.example.student_management.entities.Student;
import com.example.student_management.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Sauvegarder un étudiant
    public Student save(Student student) {
        // Optionnel : ajouter une validation ici si nécessaire
        return studentRepository.save(student);
    }

    // Supprimer un étudiant par ID
    public boolean delete(int id) {
        // Utilisation correcte de findById()
        Optional<Student> studentOptional = Optional.ofNullable(studentRepository.findById(id));
        if (studentOptional.isPresent()) {
            studentRepository.delete(studentOptional.get());
            return true;
        } else {
            return false;
        }
    }

    // Récupérer tous les étudiants
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    // Compter le nombre total d'étudiants
    public long countStudents() {
        return studentRepository.count();
    }

    // Trouver le nombre d'étudiants par année
    public Collection<Object[]> findNbrStudentByYear() {
        return studentRepository.findNbrStudentByYear();
    }
}

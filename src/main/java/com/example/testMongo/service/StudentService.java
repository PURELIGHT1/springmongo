package com.example.testMongo.service;

import com.example.testMongo.domain.dto.req.StudentReq;
import com.example.testMongo.domain.dto.res.StudentRes;
import com.example.testMongo.domain.entity.Student;
import com.example.testMongo.repository.StudentRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository repo;

    public StudentService(StudentRepository repository) {
        this.repo = repository;
    }

    public Student postStudent(StudentReq req) {
        Student student = new Student();
        student.setName(req.getName());
        student.setAddress(req.getAddress());
        return repo.save(student);
    }

    public List<StudentRes> getAll() {
        List<Student> students = repo.findAll();

        List<StudentRes> studentResList = students.stream()
                .map(student -> {
                    StudentRes res = new StudentRes();
                    res.setNo(student.getId().toString());
                    res.setName(student.getName());
                    res.setAddress(student.getAddress());
                    return res;
                })
                .collect(Collectors.toList());

        return studentResList;
    }

    public StudentRes getById(String id){
        StudentRes res = new StudentRes();
        Optional<Student> data = repo.findById(new ObjectId(id));
        if(data.isPresent()){
            res.setNo(data.get().getId().toString());
            res.setName(data.get().getName());
            res.setAddress(data.get().getAddress());
        }
        return res;
    }

    public Boolean delete(String id){
        StudentRes res = new StudentRes();
        Optional<Student> data = repo.findById(new ObjectId(id));
        if(data.isPresent()){
            repo.delete(data.get());
            return true;
        }else{
            return false;
        }
    }
}

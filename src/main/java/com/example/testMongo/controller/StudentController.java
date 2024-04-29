package com.example.testMongo.controller;

import com.example.testMongo.domain.dto.req.StudentReq;
import com.example.testMongo.domain.dto.res.StudentRes;
import com.example.testMongo.domain.entity.Student;
import com.example.testMongo.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService service;

    @PostMapping()
    public ResponseEntity<Student> create(
            @RequestBody StudentReq req
    ) {
        return ResponseEntity.ok(service.postStudent(req));
    }

    @GetMapping()
    public ResponseEntity<List<StudentRes>> findAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/detail")
    public ResponseEntity<StudentRes> getById(
            @RequestParam("id") String id
    ){
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping()
    public ResponseEntity<Boolean> delete(
            @RequestParam("id") String id
    ){
        return ResponseEntity.ok(service.delete(id));
    }
}

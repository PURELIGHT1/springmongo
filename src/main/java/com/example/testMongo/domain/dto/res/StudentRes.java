package com.example.testMongo.domain.dto.res;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class StudentRes {
    private String no;
    private String name;
    private String address;
}

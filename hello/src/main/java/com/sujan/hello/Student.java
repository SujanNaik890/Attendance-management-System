package com.sujan.hello;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
public class Student {

    @Id
    private String id;
    private String name;

    public Student() {}

    public Student(String name) {
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
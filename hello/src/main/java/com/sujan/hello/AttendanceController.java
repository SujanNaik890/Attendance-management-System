package com.sujan.hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AttendanceController {

    private final AttendanceService service;

    public AttendanceController(AttendanceService service) {
        this.service = service;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return service.getStudents();
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Map<String, String> body) {
        return service.addStudent(body.get("name"));
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable String id) {
        service.deleteStudent(id);
    }

    @GetMapping("/attendance")
    public Map<String, Boolean> getAttendance(@RequestParam String date) {
        return service.getAttendance(LocalDate.parse(date));
    }

    @PostMapping("/attendance")
    public AttendanceRecord saveAttendance(@RequestBody Map<String, Object> body) {

        String dateStr = (String) body.get("date");
        LocalDate date = LocalDate.parse(dateStr);

        @SuppressWarnings("unchecked")
        Map<String, Boolean> raw = (Map<String, Boolean>) body.get("attendance");
            if (raw == null) {
                raw = new HashMap<>();
            }

        return service.saveAttendance(date, raw);
    }

    @GetMapping("/summary")
    public Map<String, Object> getSummary(@RequestParam String date) {
        return service.getSummary(LocalDate.parse(date));
    }
}
package com.sujan.hello;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class AttendanceService {

    private final StudentRepository studentRepo;
    private final AttendanceRepository attendanceRepo;

    public AttendanceService(StudentRepository studentRepo, AttendanceRepository attendanceRepo) {
        this.studentRepo = studentRepo;
        this.attendanceRepo = attendanceRepo;
    }

    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    public Student addStudent(String name) {
        Student student = new Student(name);
        return studentRepo.save(student);
    }

    public void deleteStudent(String id) {
        if (id != null) {
            studentRepo.deleteById(id);
        }
    }

    public Map<String, Boolean> getAttendance(LocalDate date) {
        return attendanceRepo.findByDate(date)
                .map(AttendanceRecord::getAttendance)
                .orElse(new HashMap<>());
    }

    public AttendanceRecord saveAttendance(LocalDate date, Map<String, Boolean> attendance) {
        AttendanceRecord record = new AttendanceRecord(date, attendance);
        return attendanceRepo.save(record);
    }

   public Map<String, Object> getSummary(LocalDate date) {

    Map<String, Boolean> data = getAttendance(date);

    // Safety check
    if (data == null) {
        data = new HashMap<>();
    }

    int total = studentRepo.findAll().size();

    long present = data.values().stream()
            .filter(v -> v != null && v)
            .count();

    return Map.of(
            "total", total,
            "present", present,
            "absent", total - present,
            "attendanceRate", total == 0 ? 0 : (present * 100 / total)
    );
}
}
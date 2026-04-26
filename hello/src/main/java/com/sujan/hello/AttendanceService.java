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
public AttendanceRecord saveAttendance(LocalDate date, Map<String, Boolean> attendance) {

    Optional<AttendanceRecord> existing = attendanceRepo.findByDate(date);

    AttendanceRecord record;

    if (existing.isPresent()) {
        record = existing.get();
    } else {
        record = new AttendanceRecord();
    }

    // update values
    record = new AttendanceRecord(date, attendance);

    return attendanceRepo.save(record);
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

    Optional<AttendanceRecord> existing = attendanceRepo.findByDate(date);

    AttendanceRecord record;

    if (existing.isPresent()) {
        record = existing.get();
    } else {
        record = new AttendanceRecord();
    }

    // update fields properly
    record = new AttendanceRecord(date, attendance);

    return attendanceRepo.save(record);
}
}
package com.sujan.hello;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Map;

@Document(collection = "attendance")
public class AttendanceRecord {

    @Id
    private String id;
    private LocalDate date;
    private Map<String, Boolean> attendance;

    public AttendanceRecord() {}

    public AttendanceRecord(LocalDate date, Map<String, Boolean> attendance) {
        this.date = date;
        this.attendance = attendance;
    }

    public LocalDate getDate() { return date; }
    public Map<String, Boolean> getAttendance() { return attendance; }
}
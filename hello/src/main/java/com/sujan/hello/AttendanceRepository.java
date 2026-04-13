package com.sujan.hello;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface AttendanceRepository extends MongoRepository<AttendanceRecord, String> {
    Optional<AttendanceRecord> findByDate(LocalDate date);
}
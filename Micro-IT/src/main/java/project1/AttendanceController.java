package project1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<?> markAttendance(@RequestParam int studentId, @RequestParam String date, @RequestParam boolean present) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            return ResponseEntity.badRequest().body("Invalid studentId");
        }

        try {
            Date attendanceDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(date);
            Attendance attendance = new Attendance();
            attendance.setStudent(student);
            attendance.setDate(attendanceDate);
            attendance.setPresent(present);
            return ResponseEntity.ok(attendanceRepository.save(attendance));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid date format. Expected yyyy-MM-dd.");
        }
    }
}

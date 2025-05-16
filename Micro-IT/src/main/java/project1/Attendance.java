package project1;

import jakarta.persistence.*;
import java.util.Date;



@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Temporal(TemporalType.DATE)
    private Date date;
    private boolean present;

    public Attendance() {}

    public Attendance(int id, Student student, Date date, boolean present) {
        this.id = id;
        this.student = student;
        this.date = date;
        this.present = present;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public boolean isPresent() { return present; }
    public void setPresent(boolean present) { this.present = present; }
}

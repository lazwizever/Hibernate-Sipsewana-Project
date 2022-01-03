package lk.ijse.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class StudentProgram {
    @Id
    @GeneratedValue
    private int spId;
    private String stRegistrationDate;

    @ManyToOne
    private Student student;
    @ManyToOne
    private Program program;

    public StudentProgram() {
    }

    public StudentProgram(int spId, String stRegistrationDate, Student student, Program program) {
        this.setSpId(spId);
        this.setStRegistrationDate(stRegistrationDate);
        this.setStudent(student);
        this.setProgram(program);
    }

    public int getSpId() {
        return spId;
    }

    public void setSpId(int spId) {
        this.spId = spId;
    }

    public String getStRegistrationDate() {
        return stRegistrationDate;
    }

    public void setStRegistrationDate(String stRegistrationDate) {
        this.stRegistrationDate = stRegistrationDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}

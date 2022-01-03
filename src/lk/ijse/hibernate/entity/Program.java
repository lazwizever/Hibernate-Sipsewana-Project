package lk.ijse.hibernate.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Program {
    @Id
    private String programId;
    private String programName;
    private String programFee;
    private String duration;

    @OneToMany(mappedBy = "program",cascade = CascadeType.ALL)
    private List<StudentProgram> studentProgram;

    public Program() {
    }

    public Program(String programId, String programName, String programFee, String duration, List<StudentProgram> studentProgram) {
        this.setProgramId(programId);
        this.setProgramName(programName);
        this.setProgramFee(programFee);
        this.setDuration(duration);
        this.setStudentProgram(studentProgram);
    }

    public Program(String programId, String programName, String programFee, String duration) {
        this.setProgramId(programId);
        this.setProgramName(programName);
        this.setProgramFee(programFee);
        this.setDuration(duration);
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramFee() {
        return programFee;
    }

    public void setProgramFee(String programFee) {
        this.programFee = programFee;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<StudentProgram> getStudentProgram() {
        return studentProgram;
    }

    public void setStudentProgram(List<StudentProgram> studentProgram) {
        this.studentProgram = studentProgram;
    }
}

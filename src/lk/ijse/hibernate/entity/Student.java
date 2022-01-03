package lk.ijse.hibernate.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student {
    @Id
    private String stRegistrationNO;
    private String stName;
    private String sstRegistrationDate;
    private String stAddress;
    private String nic;
    private String contactNO;
    private String eMail;
    private String stGender;
    private String  stDOB;
    @ManyToOne
    private Parent parent;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<StudentProgram> studentProgram;

    public Student() {
    }

    public Student(String stRegistrationNO, String stName, String sstRegistrationDate, String stAddress, String nic, String contactNO, String eMail, String stGender, String stDOB, Parent parent, List<StudentProgram> studentProgram) {
        this.stRegistrationNO = stRegistrationNO;
        this.stName = stName;
        this.sstRegistrationDate = sstRegistrationDate;
        this.stAddress = stAddress;
        this.nic = nic;
        this.contactNO = contactNO;
        this.eMail = eMail;
        this.stGender = stGender;
        this.stDOB = stDOB;
        this.parent = parent;
        this.studentProgram = studentProgram;
    }

    public Student(String stRegistrationNO, String stName, String sstRegistrationDate, String stAddress, String nic, String contactNO, String eMail, String stGender, String stDOB, Parent parent) {
        this.stRegistrationNO = stRegistrationNO;
        this.stName = stName;
        this.sstRegistrationDate = sstRegistrationDate;
        this.stAddress = stAddress;
        this.nic = nic;
        this.contactNO = contactNO;
        this.eMail = eMail;
        this.stGender = stGender;
        this.stDOB = stDOB;
        this.parent = parent;
    }

    public String getStRegistrationNO() {
        return stRegistrationNO;
    }

    public void setStRegistrationNO(String stRegistrationNO) {
        this.stRegistrationNO = stRegistrationNO;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getSstRegistrationDate() {
        return sstRegistrationDate;
    }

    public void setSstRegistrationDate(String sstRegistrationDate) {
        this.sstRegistrationDate = sstRegistrationDate;
    }

    public String getStAddress() {
        return stAddress;
    }

    public void setStAddress(String stAddress) {
        this.stAddress = stAddress;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getContactNO() {
        return contactNO;
    }

    public void setContactNO(String contactNO) {
        this.contactNO = contactNO;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getStGender() {
        return stGender;
    }

    public void setStGender(String stGender) {
        this.stGender = stGender;
    }

    public String getStDOB() {
        return stDOB;
    }

    public void setStDOB(String stDOB) {
        this.stDOB = stDOB;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public List<StudentProgram> getStudentProgram() {
        return studentProgram;
    }

    public void setStudentProgram(List<StudentProgram> studentProgram) {
        this.studentProgram = studentProgram;
    }
}

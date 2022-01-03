package lk.ijse.hibernate.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Parent {
    @Id
    private String pId;
    private String pstRegistrationNO;
    private String pName;
    private String pAddress;
    private String NIC;
    private String pJob;
    private String pTitle;
    private String gender;
    private String pContactNO;

    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL)
    private
    List<Student> studentList;

    public Parent() {
    }


    public Parent(String pId, String pstRegistrationNO, String pName, String pAddress, String NIC, String pJob, String pTitle, String gender, String pContactNO) {
        this.pId = pId;
        this.pstRegistrationNO = pstRegistrationNO;
        this.pName = pName;
        this.pAddress = pAddress;
        this.NIC = NIC;
        this.pJob = pJob;
        this.pTitle = pTitle;
        this.gender = gender;
        this.pContactNO = pContactNO;
    }



    public Parent(String pId, String pstRegistrationNO, String pName, String pAddress, String NIC, String pJob, String pTitle, String gender, String pContactNO, List<Student> studentList) {
        this.setpId(pId);
        this.setPstRegistrationNO(pstRegistrationNO);
        this.setpName(pName);
        this.setpAddress(pAddress);
        this.setNIC(NIC);
        this.setpJob(pJob);
        this.setpTitle(pTitle);
        this.setGender(gender);
        this.setpContactNO(pContactNO);
        this.setStudentList(studentList);
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getPstRegistrationNO() {
        return pstRegistrationNO;
    }

    public void setPstRegistrationNO(String pstRegistrationNO) {
        this.pstRegistrationNO = pstRegistrationNO;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getpJob() {
        return pJob;
    }

    public void setpJob(String pJob) {
        this.pJob = pJob;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getpContactNO() {
        return pContactNO;
    }

    public void setpContactNO(String pContactNO) {
        this.pContactNO = pContactNO;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}

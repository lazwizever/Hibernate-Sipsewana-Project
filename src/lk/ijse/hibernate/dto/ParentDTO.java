package lk.ijse.hibernate.dto;

import java.util.List;

public class ParentDTO {
    private String pId;
    private String stRegistrationNO;
    private String pName;
    private String pAddress;
    private String pNIC;
    private String pJob;
    private String pTitle;
    private String pGender;
    private String pContactNO;
    private List<StudentDTO> studentList;

    public ParentDTO() {
    }

    public ParentDTO(String pId, String stRegistrationNO, String pName, String pAddress, String pNIC, String pJob, String pTitle, String pGender, String pContactNO, List<StudentDTO> studentList) {
        this.pId = pId;
        this.stRegistrationNO = stRegistrationNO;
        this.pName = pName;
        this.pAddress = pAddress;
        this.pNIC = pNIC;
        this.pJob = pJob;
        this.pTitle = pTitle;
        this.pGender = pGender;
        this.pContactNO = pContactNO;
        this.studentList = studentList;
    }

    public ParentDTO(String pId, String stRegistrationNO, String pName, String pAddress, String pNIC, String pJob, String pTitle, String pGender, String pContactNO) {
        this.pId = pId;
        this.stRegistrationNO = stRegistrationNO;
        this.pName = pName;
        this.pAddress = pAddress;
        this.pNIC = pNIC;
        this.pJob = pJob;
        this.pTitle = pTitle;
        this.pGender = pGender;
        this.pContactNO = pContactNO;
    }



    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getStRegistrationNO() {
        return stRegistrationNO;
    }

    public void setStRegistrationNO(String stRegistrationNO) {
        this.stRegistrationNO = stRegistrationNO;
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

    public String getpNIC() {
        return pNIC;
    }

    public void setpNIC(String pNIC) {
        this.pNIC = pNIC;
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

    public String getpGender() {
        return pGender;
    }

    public void setpGender(String pGender) {
        this.pGender = pGender;
    }

    public String getpContactNO() {
        return pContactNO;
    }

    public void setpContactNO(String pContactNO) {
        this.pContactNO = pContactNO;
    }

    public List<StudentDTO> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentDTO> studentList) {
        this.studentList = studentList;
    }
}

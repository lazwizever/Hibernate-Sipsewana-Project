package lk.ijse.hibernate.dto;

public class StudentDTO {
    private String stRegistrationNo;
    private String stName;
    private String stAddress;
    private String stRegistrationDate;
    private String stEmail;
    private String stNIC;
    private String stContactNo;
    private String gender;
    private String DOB;
    private ParentDTO parentDTO;

    public StudentDTO() {
    }

    public StudentDTO(String stRegistrationNo, String stName, String stAddress, String stRegistrationDate, String stEmail, String stNIC, String stContactNo, String gender, String DOB) {
        this.setStRegistrationNo(stRegistrationNo);
        this.setStName(stName);
        this.setStAddress(stAddress);
        this.setStRegistrationDate(stRegistrationDate);
        this.setStEmail(stEmail);
        this.setStNIC(stNIC);
        this.setStContactNo(stContactNo);
        this.setGender(gender);
        this.setDOB(DOB);
    }



    public StudentDTO(String stRegistrationNo, String stName, String stAddress, String stRegistrationDate, String stEmail, String stNIC, String stContactNo, String gender, String DOB, ParentDTO parentDTO) {
        this.setStRegistrationNo(stRegistrationNo);
        this.setStName(stName);
        this.setStAddress(stAddress);
        this.setStRegistrationDate(stRegistrationDate);
        this.setStEmail(stEmail);
        this.setStNIC(stNIC);
        this.setStContactNo(stContactNo);
        this.setGender(gender);
        this.setDOB(DOB);
        this.setParentDTO(parentDTO);
    }

    public String getStRegistrationNo() {
        return stRegistrationNo;
    }

    public void setStRegistrationNo(String stRegistrationNo) {
        this.stRegistrationNo = stRegistrationNo;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStAddress() {
        return stAddress;
    }

    public void setStAddress(String stAddress) {
        this.stAddress = stAddress;
    }

    public String getStRegistrationDate() {
        return stRegistrationDate;
    }

    public void setStRegistrationDate(String stRegistrationDate) {
        this.stRegistrationDate = stRegistrationDate;
    }

    public String getStEmail() {
        return stEmail;
    }

    public void setStEmail(String stEmail) {
        this.stEmail = stEmail;
    }

    public String getStNIC() {
        return stNIC;
    }

    public void setStNIC(String stNIC) {
        this.stNIC = stNIC;
    }

    public String getStContactNo() {
        return stContactNo;
    }

    public void setStContactNo(String stContactNo) {
        this.stContactNo = stContactNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public ParentDTO getParentDTO() {
        return parentDTO;
    }

    public void setParentDTO(ParentDTO parentDTO) {
        this.parentDTO = parentDTO;
    }
}

package lk.ijse.hibernate.dto;

public class StudentProgramDTO {
    private String spId;
    private String programId;
    private String stRegistrationNo;
    private String stRegistrationDate;

    public StudentProgramDTO() {
    }

    public StudentProgramDTO(String spId, String programId, String stRegistrationNo, String stRegistrationDate) {
        this.setSpId(spId);
        this.setProgramId(programId);
        this.setStRegistrationNo(stRegistrationNo);
        this.setStRegistrationDate(stRegistrationDate);
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getStRegistrationNo() {
        return stRegistrationNo;
    }

    public void setStRegistrationNo(String stRegistrationNo) {
        this.stRegistrationNo = stRegistrationNo;
    }

    public String getStRegistrationDate() {
        return stRegistrationDate;
    }

    public void setStRegistrationDate(String stRegistrationDate) {
        this.stRegistrationDate = stRegistrationDate;
    }
}

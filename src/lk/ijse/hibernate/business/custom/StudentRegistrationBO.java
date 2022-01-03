package lk.ijse.hibernate.business.custom;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.business.SuperBO;
import lk.ijse.hibernate.dto.ParentDTO;
import lk.ijse.hibernate.dto.ProgramDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.dto.StudentProgramDTO;

import java.sql.SQLException;

public interface StudentRegistrationBO extends SuperBO {
    ObservableList<StudentProgramDTO> getStudentProgramDetails() throws SQLException, ClassNotFoundException;

    boolean saveStudentAndGuardian(ParentDTO parentDTO) throws SQLException, ClassNotFoundException;

    /*  public String generateStudentId() throws SQLException, ClassNotFoundException;*/

    ObservableList<StudentDTO> getAllStudentDetails() throws SQLException, ClassNotFoundException;

    boolean updateStudentAndGuardian(ParentDTO parentDTO) throws SQLException, ClassNotFoundException;

    ObservableList<StudentDTO> getStudentForName(String text) throws SQLException, ClassNotFoundException;

    String generateStudentId();

    String generateParentId();

    ObservableList<ProgramDTO> getProgramDetailsFromProgramType(String newValue);

    ObservableList<ProgramDTO> getProgramsForId(String programId);
}

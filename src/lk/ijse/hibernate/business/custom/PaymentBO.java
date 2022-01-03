package lk.ijse.hibernate.business.custom;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.business.SuperBO;
import lk.ijse.hibernate.dto.ProgramDTO;
import lk.ijse.hibernate.dto.StudentProgramDTO;

import java.sql.SQLException;

public interface PaymentBO extends SuperBO {

    boolean makePayment(StudentProgramDTO studentProgramDTO) throws SQLException, ClassNotFoundException;

    ObservableList<StudentProgramDTO> getPaymentDetails(String stRegistrationNo) throws SQLException, ClassNotFoundException;

    ObservableList<StudentProgramDTO> getStudentProgramDetails() throws SQLException, ClassNotFoundException;


    ObservableList<ProgramDTO> getAllPrograms() throws SQLException, ClassNotFoundException;

    ObservableList<ProgramDTO> getProgramDetailsFromProgramType(String newValue);

    String getProgramId(String value);
}

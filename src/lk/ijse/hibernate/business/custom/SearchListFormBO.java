package lk.ijse.hibernate.business.custom;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.business.SuperBO;
import lk.ijse.hibernate.dto.StudentDTO;

import java.sql.SQLException;

public interface SearchListFormBO extends SuperBO{
    ObservableList<StudentDTO> getAllStudentDetails() throws SQLException, ClassNotFoundException;

    ObservableList<StudentDTO> getStudentForName(String text);
}

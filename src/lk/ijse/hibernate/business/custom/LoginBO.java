package lk.ijse.hibernate.business.custom;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.business.SuperBO;
import lk.ijse.hibernate.dto.LoginDTO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    ObservableList<LoginDTO> getAllDetails() throws SQLException, ClassNotFoundException;

}

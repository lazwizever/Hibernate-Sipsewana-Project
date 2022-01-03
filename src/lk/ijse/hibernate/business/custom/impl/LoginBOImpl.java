package lk.ijse.hibernate.business.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.hibernate.business.custom.LoginBO;
import lk.ijse.hibernate.dto.LoginDTO;
import lk.ijse.hibernate.entity.LoginMaster;
import lk.ijse.hibernate.repository.DAOFactory;
import lk.ijse.hibernate.repository.cusom.LoginDAO;
import lk.ijse.hibernate.repository.cusom.ProgramDAO;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {

    LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN);

    @Override
    public ObservableList<LoginDTO> getAllDetails() throws SQLException, ClassNotFoundException {
        ObservableList<LoginMaster> allDetails = loginDAO.getAll();
        ObservableList<LoginDTO> loginObList = FXCollections.observableArrayList();

        for (LoginMaster temp : allDetails) {
            LoginDTO loginDTO = new LoginDTO(
                    temp.getPassword(),temp.getUserName()
            );
            loginObList.add(loginDTO);
        }
        return loginObList;
    }
}

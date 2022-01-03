package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.business.BOFactory;
import lk.ijse.hibernate.business.custom.LoginBO;
import lk.ijse.hibernate.business.custom.PaymentBO;
import lk.ijse.hibernate.dto.LoginDTO;
import lk.ijse.hibernate.repository.DAOFactory;
import lk.ijse.hibernate.repository.cusom.PaymentDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class LoginFormController {
    public AnchorPane loginContext;
    public TextField txtUserName;
    public PasswordField txtPassWord;
    public Label lblUserNameWarningMessage;
    public Label passwordWarningMessage;
    public JFXCheckBox checkBoxShowPassword;
    public TextField txtShowPassword;

    LoginBO loginBO = (LoginBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LOGIN);

    public void initialize(){
        checkBoxShowPassword.selectedProperty().addListener((observable, oldValue, newValue) ->{
            showPassword(newValue);
        });
    }

    private void showPassword(Boolean newValue) {
       if (newValue==true){
            txtShowPassword.setText(txtPassWord.getText());
            txtShowPassword.setVisible(true);
       }else {
           txtShowPassword.setVisible(false);
       }
    }


    public void loginOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        ObservableList<LoginDTO> loginDetails = loginBO.getAllDetails();

        for (LoginDTO temp : loginDetails) {
            if (txtUserName.getText().equals(temp.getUserName())){
                if (txtPassWord.getText().equals(temp.getPassword())){
                    URL resource = getClass().getResource("../view/UserForm.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Stage window = (Stage) loginContext.getScene().getWindow();
                    window.setX(550);
                    window.setY(100);
                    window.setScene(new Scene(load));
                }else {
                    passwordWarningMessage.setVisible(true);
                }
            }else {
                lblUserNameWarningMessage.setVisible(true);
            }
        }

    }

    public void invisibleUserNameWarningMessage(MouseEvent mouseEvent) {
        lblUserNameWarningMessage.setVisible(false);
    }

    public void invisiblePasswordWarningMessage(MouseEvent mouseEvent) {
        passwordWarningMessage.setVisible(false);
    }
}

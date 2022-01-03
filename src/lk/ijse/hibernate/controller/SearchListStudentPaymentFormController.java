package lk.ijse.hibernate.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.business.BOFactory;
import lk.ijse.hibernate.business.custom.SearchListFormBO;
import lk.ijse.hibernate.business.custom.StudentRegistrationBO;
import lk.ijse.hibernate.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;

public class SearchListStudentPaymentFormController {
    public TableColumn colRegistrationNo;
    public TableColumn colStudentName;
    public AnchorPane searchStudentListContext;
    public TableView<StudentDTO> tblStudentDetails;
    public TextField txtSearchBar;


    SearchListFormBO searchListFormBO= (SearchListFormBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SEARCH);
    public void initialize(){
        colRegistrationNo.setCellValueFactory(new PropertyValueFactory<>("stRegistrationNo"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("stName"));

        try {
            setStudentDetailsTbl();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblStudentDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/PaymentForm.fxml"));
                try {
                    Parent parent = loader.load();
                    PaymentFormController controller = loader.getController();
                    controller.setStudentDetailsToTextField(newValue);
                    Stage window = (Stage) searchStudentListContext.getScene().getWindow();
                    window.setScene(new Scene(parent));

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void setStudentDetailsTbl() throws SQLException, ClassNotFoundException {
        ObservableList<StudentDTO> allStudentDetails = searchListFormBO.getAllStudentDetails();
        tblStudentDetails.setItems(allStudentDetails);
    }


    public void searchStudentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ObservableList<StudentDTO> studentForName = searchListFormBO.getStudentForName(txtSearchBar.getText());
        tblStudentDetails.setItems(studentForName);
    }
}

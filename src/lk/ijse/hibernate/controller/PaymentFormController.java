package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.business.BOFactory;
import lk.ijse.hibernate.business.custom.PaymentBO;
import lk.ijse.hibernate.business.custom.ProgramBO;
import lk.ijse.hibernate.business.custom.StudentRegistrationBO;
import lk.ijse.hibernate.dto.ProgramDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.dto.StudentProgramDTO;
import lk.ijse.hibernate.entity.Program;
import lk.ijse.hibernate.repository.DAOFactory;
import lk.ijse.hibernate.repository.cusom.PaymentDAO;
import lk.ijse.hibernate.repository.cusom.ProgramDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PaymentFormController {
    public AnchorPane paymentContext;
    public JFXTextField txtRegistrationNo;
    public JFXTextField txtStName;
    public JFXComboBox<String> cmbFeeType;
    public JFXTextField txtAddress;
    public JFXTextField txtDOB;
    public JFXTextField txtContactNo;
    public JFXTextField txtEmail;
    public Label lblPaymentId;
    public Label lblDate;
    public Label lblAmount;
    public Label lblTime;
    public Label lblBalance;
    public TextField txtCash;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PAYMENT);

    public void initialize(){

        {
            Thread clock=new Thread(){
                public void run(){
                    try {
                        while (true) {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy ");
                            SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm a");
                            Date date = new Date();
                            String myString = formatter.format(date);
                            String myString1 = formatter1.format(date);
                            Platform.runLater(() -> {
                                lblDate.setText(myString);
                                lblTime.setText(myString1);
                            });
                            sleep(1);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            clock.start();

        }


        try {
            ObservableList<ProgramDTO> all = paymentBO.getAllPrograms();

            ObservableList<String> programs = FXCollections.observableArrayList();
            for (ProgramDTO temp : all) {
                programs.add(temp.getName());
            }
            cmbFeeType.setItems(programs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        cmbFeeType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<ProgramDTO> programDetailsFromProgramType = paymentBO.getProgramDetailsFromProgramType(newValue);

            for (ProgramDTO temp : programDetailsFromProgramType) {
                lblAmount.setText(temp.getFee());
            }

        });

    }

    public void backToUserFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/UserForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) paymentContext.getScene().getWindow();
        window.setX(550);
        window.setY(100);
        window.setScene(new Scene(load));
    }

    public void moveToSearchStudentListOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SearchListStudentPaymentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) paymentContext.getScene().getWindow();
        window.setX(650);
        window.setY(300);
        window.setScene(new Scene(load));
    }

    public void setStudentDetailsToTextField(StudentDTO newValue) {
        txtRegistrationNo.setText(newValue.getStRegistrationNo());
        txtStName.setText(newValue.getStName());
        txtAddress.setText(newValue.getStAddress());
        txtEmail.setText(newValue.getStEmail());
        txtDOB.setText(newValue.getDOB());
        txtContactNo.setText(newValue.getStContactNo());

    }

    public void addPaymentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       if (validateProgram()){
            String programId = paymentBO.getProgramId(cmbFeeType.getValue());

                StudentProgramDTO studentProgramDTO = new StudentProgramDTO();
                studentProgramDTO.setProgramId(programId);
                studentProgramDTO.setStRegistrationNo(txtRegistrationNo.getText());
                studentProgramDTO.setStRegistrationDate(lblDate.getText());

                paymentBO.makePayment(studentProgramDTO);
                clearAllFields();
           new Alert(Alert.AlertType.INFORMATION,"Student has been registered to the program Successfully! ").show();
       }

    }

    public boolean validateProgram() throws SQLException, ClassNotFoundException {
        ObservableList<StudentProgramDTO> studentProgramDetails = paymentBO.getStudentProgramDetails();

        L1: for (StudentProgramDTO temp : studentProgramDetails) {
            if (txtRegistrationNo.getText().equals(temp.getStRegistrationNo())){

                ObservableList<ProgramDTO> programDetailsFromProgramType = paymentBO.getProgramDetailsFromProgramType(cmbFeeType.getValue());

                for (ProgramDTO temp1 : programDetailsFromProgramType) {
                    if (temp1.getId().equals(temp.getProgramId())){
                        new Alert(Alert.AlertType.WARNING,"This student has been already registered to this program ").show();
                        return false;
                    }
                }
            }else {
                continue L1;

            }
        }
        return true;
    }

    public void clearFieldsOnAction(ActionEvent actionEvent) {
       clearAllFields();
    }

    private void clearAllFields(){
        txtRegistrationNo.clear();
        txtDOB.clear();
        txtContactNo.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtStName.clear();
        lblBalance.setText("0.00");
        lblAmount.setText("0.00");
        txtCash.clear();
    }

    public void cashOnAction(ActionEvent actionEvent) {
        if (Double.parseDouble(txtCash.getText())<Double.parseDouble(lblAmount.getText())){
            txtCash.setStyle("-fx-border-color: red;"+"-fx-border-width: 1;");
        }else {
            Double balance = Double.parseDouble(txtCash.getText()) - Double.parseDouble(lblAmount.getText());
            lblBalance.setText(String.valueOf(balance));
            txtCash.setStyle("-fx-border-color: white;"+"-fx-border-width: 1;");
        }
    }
}

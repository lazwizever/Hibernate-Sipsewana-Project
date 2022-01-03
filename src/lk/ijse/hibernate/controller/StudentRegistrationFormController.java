package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.business.BOFactory;
import lk.ijse.hibernate.business.custom.PaymentBO;
import lk.ijse.hibernate.business.custom.ProgramBO;
import lk.ijse.hibernate.business.custom.StudentRegistrationBO;
import lk.ijse.hibernate.dto.ParentDTO;
import lk.ijse.hibernate.dto.ProgramDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.dto.StudentProgramDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class StudentRegistrationFormController {
    public AnchorPane studentRegistrationContext;
    public Label lblRegistrationNo;
    public JFXTextField txtStNIC;
    public JFXTextField txtAddress;
    public RadioButton rdBtnMale;
    public RadioButton rdBtnFemale;
    public ComboBox<String> cmbDay;
    public ComboBox<String> cmbMonth;
    public ComboBox<String> cmbYear;
    public JFXTextField txtStudentName;
    public JFXTextField txtContactNo1;
    public JFXTextField txtEmail;
    public Label lblDate;
    public JFXTextField txtParentName;
    public RadioButton rdMale;
    public ComboBox<String> cmbParentTitle;
    public RadioButton rdFemale;
    public JFXTextField txtParentJob;
    public JFXTextField txtNIC;
    public JFXTextField txtParentAddress;
    public JFXTextField txtParentContactNo;
    public Label lblParentId;
    public JFXTextField txtRegistrationNo;
    public JFXTextField txtParentId;
    public JFXComboBox<String> cmbPrograms;
    public JFXTextField txtProgramId;
    public JFXTextField txtRegistrationDate;
    public RadioButton rdBtnAddNewStudent;
    public RadioButton rdBtnExistingStudent;
    public JFXButton btnSearch;
    public JFXButton btnRegisterStudent;
    public Label lblTime;
    public TextField txtInputRegistrationNo;
    public TextField txtInputPrtRegistrationNo;

    StudentRegistrationBO studentRegistrationBO = (StudentRegistrationBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.STUDENT);


    LinkedHashMap<JFXTextField, Pattern> guardianMap = new LinkedHashMap<>();
    LinkedHashMap<JFXTextField, Pattern> studentMap = new LinkedHashMap<>();

    Pattern name = Pattern.compile("^([A-z.\\s]{3,})$");
    Pattern contactNo = Pattern.compile("^([0-9]{10})$");
    Pattern address = Pattern.compile("^([A-z0-9,/\\s]{3,})$");
    Pattern nic = Pattern.compile("^([0-9]{9}[v|V]|[0-9]{12})$");
    Pattern job = Pattern.compile("^([A-z\\s]{3,})$");
    Pattern email = Pattern.compile("^([a-z0-9]{4,}\\@gmail.com)$");


    public void initialize() {
        storeValidation();
        txtInputRegistrationNo.setText(studentRegistrationBO.generateStudentId());
        txtInputPrtRegistrationNo.setText(studentRegistrationBO.generateParentId());

        {
            Thread clock = new Thread() {
                public void run() {
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


        ObservableList<String> days = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30");
        cmbDay.setItems(days);

        ObservableList<String> months = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
        cmbMonth.setItems(months);

        ObservableList<String> years = FXCollections.observableArrayList("1996", "1997", "1998", "1999", "2000", "2001", "2002");
        cmbYear.setItems(years);

        ObservableList<String> parentTitle = FXCollections.observableArrayList("Mr", "Mrs");
        cmbParentTitle.setItems(parentTitle);


        cmbPrograms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setProgramDetailsToFields(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        rdBtnAddNewStudent.setSelected(true);
        rdBtnExistingStudent.setSelected(false);
        txtRegistrationNo.setDisable(true);
        btnSearch.setDisable(true);
        cmbPrograms.setDisable(true);
        txtRegistrationDate.setDisable(true);
        txtProgramId.setDisable(true);


        for (JFXTextField jfxTextField : studentMap.keySet()) {
            jfxTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                btnRegisterStudent.setDisable(!validateStudentFinal() || !validateGuardianFinal());
            });
        }


        for (JFXTextField jfxTextField : guardianMap.keySet()) {
            jfxTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                btnRegisterStudent.setDisable(!validateStudentFinal() || !validateGuardianFinal());
            });
        }

        cmbDay.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnRegisterStudent.setDisable(!validateStudentFinal() || !validateGuardianFinal());
        });

        cmbMonth.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnRegisterStudent.setDisable(!validateStudentFinal() || !validateGuardianFinal());
        });

        cmbYear.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnRegisterStudent.setDisable(!validateStudentFinal() || !validateGuardianFinal());
        });


        rdBtnFemale.selectedProperty().addListener((observable, oldValue, newValue) -> {
            btnRegisterStudent.setDisable(!validateStudentFinal() || !validateGuardianFinal());
        });

        rdBtnMale.selectedProperty().addListener((observable, oldValue, newValue) -> {
            btnRegisterStudent.setDisable(!validateStudentFinal() || !validateGuardianFinal());
        });

        cmbParentTitle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnRegisterStudent.setDisable(!validateStudentFinal() || !validateGuardianFinal());
        });

        rdMale.selectedProperty().addListener((observable, oldValue, newValue) -> {
            btnRegisterStudent.setDisable(!validateStudentFinal() || !validateGuardianFinal());
        });

        rdBtnFemale.selectedProperty().addListener((observable, oldValue, newValue) -> {
            btnRegisterStudent.setDisable(!validateStudentFinal() || !validateGuardianFinal());
        });

        rdFemale.selectedProperty().addListener((observable, oldValue, newValue) -> {
            btnRegisterStudent.setDisable(!validateStudentFinal() || !validateGuardianFinal());
        });
    }

    private void storeValidation() {
        studentMap.put(txtStudentName, name);
        studentMap.put(txtAddress, address);
        studentMap.put(txtContactNo1, contactNo);
        studentMap.put(txtEmail, email);
        studentMap.put(txtStNIC, nic);

        guardianMap.put(txtParentName, name);
        guardianMap.put(txtParentAddress, address);
        guardianMap.put(txtNIC, nic);
        guardianMap.put(txtParentJob, job);
        guardianMap.put(txtParentContactNo, contactNo);

    }

    public boolean validateStudentDetails() {
        for (JFXTextField jfxTextField : studentMap.keySet()) {
            Pattern pattern = studentMap.get(jfxTextField);
            if (!pattern.matcher(jfxTextField.getText()).matches()) {
                jfxTextField.setStyle("-fx-border-color: red");
                return false;
            } else {
                jfxTextField.setStyle("-fx-border-color: green");
            }
        }
        return true;
    }

    public boolean validateGuardian() {
        for (JFXTextField jfxTextField : guardianMap.keySet()) {
            Pattern pattern = guardianMap.get(jfxTextField);
            if (!pattern.matcher(jfxTextField.getText()).matches()) {
                jfxTextField.setStyle("-fx-border-color: red");
                return false;
            } else {
                jfxTextField.setStyle("-fx-border-color: green");
            }
        }
        return true;
    }

    public boolean validateStudentFinal() {
        boolean validatestudent = validateStudentDetails();
        boolean birthDaySelected = cmbDay.getValue() != null && cmbMonth.getValue() != null && cmbYear.getValue() != null;
        boolean studentGender = rdBtnMale.isSelected() || rdBtnFemale.isSelected();

        return validatestudent && birthDaySelected && studentGender;
    }

    public boolean validateGuardianFinal() {
        boolean validateGuardianDetails = validateGuardian();
        boolean titleSelected = cmbParentTitle.getValue() != null;
        boolean parentGender = rdMale.isSelected() || rdFemale.isSelected();

        return validateGuardianDetails && titleSelected && parentGender;
    }


    private void setProgramDetailsToFields(String newValue) throws SQLException, ClassNotFoundException {
        ObservableList<ProgramDTO> programDetailsFromProgramType = studentRegistrationBO.getProgramDetailsFromProgramType(newValue);

        for (ProgramDTO programDTO : programDetailsFromProgramType) {
            txtProgramId.setText(programDTO.getId());

            ObservableList<StudentProgramDTO> studentProgramDetails = studentRegistrationBO.getStudentProgramDetails();
            for (StudentProgramDTO temp : studentProgramDetails) {
                if (programDTO.getId().equals(temp.getProgramId())) {
                    txtRegistrationDate.setText(temp.getStRegistrationDate());
                }
            }

        }
    }

    public void setStudentDetailsToTextField(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {
        txtRegistrationNo.setText(studentDTO.getStRegistrationNo());
        txtStudentName.setText(studentDTO.getStName());
        txtAddress.setText(studentDTO.getStAddress());
        txtStNIC.setText(studentDTO.getStNIC());
        txtContactNo1.setText(studentDTO.getStContactNo());
        txtEmail.setText(studentDTO.getStEmail());

        if (studentDTO.getGender().equals("Male")) {
            rdBtnMale.setSelected(true);
        } else {
            rdBtnFemale.setSelected(true);
        }

        String day = studentDTO.getDOB().split("/")[0];
        String month = studentDTO.getDOB().split("/")[1];
        String year = studentDTO.getDOB().split("/")[2];

        cmbDay.setValue(day);
        cmbMonth.setValue(month);
        cmbYear.setValue(year);

        txtParentId.setText(studentDTO.getParentDTO().getpId());

        txtParentName.setText(studentDTO.getParentDTO().getpName());
        txtParentAddress.setText(studentDTO.getParentDTO().getpAddress());
        txtParentContactNo.setText(studentDTO.getParentDTO().getpContactNO());
        txtParentJob.setText(studentDTO.getParentDTO().getpJob());
        txtNIC.setText(studentDTO.getParentDTO().getpNIC());
        cmbParentTitle.setValue(studentDTO.getParentDTO().getpTitle());

        if (studentDTO.getParentDTO().getpGender().equals("Male")) {
            rdMale.setSelected(true);
        } else {
            rdFemale.setSelected(true);
        }

        ObservableList<StudentProgramDTO> studentProgramDetails = studentRegistrationBO.getStudentProgramDetails();
        ObservableList<String> programNames = FXCollections.observableArrayList();

        for (StudentProgramDTO temp : studentProgramDetails) {
            if (studentDTO.getStRegistrationNo().equals(temp.getStRegistrationNo())) {

                ObservableList<ProgramDTO> programsList = studentRegistrationBO.getProgramsForId(temp.getProgramId());

                for (ProgramDTO temp1 : programsList) {
                    programNames.addAll(temp1.getName());
                    cmbPrograms.setItems(programNames);

                }
            }
        }
    }

    public void backToUserForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/UserForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) studentRegistrationContext.getScene().getWindow();
        window.setX(550);
        window.setY(100);
        window.setScene(new Scene(load));
    }

    public void moveTosearchStudentList(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SearchStudentFormRegistration.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) studentRegistrationContext.getScene().getWindow();
        window.setX(700);
        window.setY(300);
        window.setScene(new Scene(load));
    }

    public void studentRegistrationOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {

        if (btnRegisterStudent.getText().equals("Next")) {

            String prtGender = (rdMale.isSelected()) ? "Male" : (rdFemale.isSelected()) ? "Female" : null;
            String prtTitle = cmbParentTitle.getValue();

            ParentDTO parentDTO = new ParentDTO(
                    txtInputPrtRegistrationNo.getText(), txtInputRegistrationNo.getText(), txtParentName.getText(), txtParentAddress.getText(), txtStNIC.getText(), txtParentJob.getText(),
                    prtTitle, prtGender, txtContactNo1.getText()
            );


            String stGender = (rdBtnMale.isSelected()) ? "Male" : (rdBtnFemale.isSelected()) ? "Female" : null;
            String dob = cmbDay.getValue() + "/" + cmbMonth.getValue() + "/" + cmbYear.getValue();
            StudentDTO studentDTO = new StudentDTO(
                    txtInputRegistrationNo.getText(), txtStudentName.getText(), txtAddress.getText(), lblDate.getText(), txtEmail.getText(), txtStNIC.getText(), txtContactNo1.getText(), stGender, dob
            );

            List<StudentDTO> studentDTOList = new ArrayList<>();
            studentDTOList.add(studentDTO);
            parentDTO.setStudentList(studentDTOList);


            if (studentRegistrationBO.saveStudentAndGuardian(parentDTO)) {

            } else {
                new Alert(Alert.AlertType.WARNING, " 'Try Again' ").show();
            }

            URL resource = getClass().getResource("../view/PaymentForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) studentRegistrationContext.getScene().getWindow();
            window.setX(700);
            window.setY(300);
            window.setScene(new Scene(load));

        } else {

            String prtGender = (rdMale.isSelected()) ? "Male" : (rdFemale.isSelected()) ? "Female" : null;
            String prtTitle = cmbParentTitle.getValue();

            ParentDTO parentDTO = new ParentDTO(
                    txtParentId.getText(), txtRegistrationNo.getText(), txtParentName.getText(), txtParentAddress.getText(), txtStNIC.getText(), txtParentJob.getText(),
                    prtTitle, prtGender, txtContactNo1.getText()
            );


            String stGender = (rdBtnMale.isSelected()) ? "Male" : (rdBtnFemale.isSelected()) ? "Female" : null;
            String dob = cmbDay.getValue() + "/" + cmbMonth.getValue() + "/" + cmbYear.getValue();
            StudentDTO studentDTO = new StudentDTO(
                    txtRegistrationNo.getText(), txtStudentName.getText(), txtAddress.getText(), lblDate.getText(), txtEmail.getText(), txtStNIC.getText(), txtContactNo1.getText(), stGender, dob
            );

            List<StudentDTO> studentDTOList = new ArrayList<>();
            studentDTOList.add(studentDTO);
            parentDTO.setStudentList(studentDTOList);


            if (studentRegistrationBO.updateStudentAndGuardian(parentDTO)) {
                    new Alert(Alert.AlertType.INFORMATION,"Updated..").show();
            }


        }
        clearAllFields();

    }

    private void clearAllFields() {
        txtInputRegistrationNo.setText(studentRegistrationBO.generateStudentId());
        txtInputPrtRegistrationNo.setText(studentRegistrationBO.generateParentId());
        for (JFXTextField jfxTextField : guardianMap.keySet()) {
            jfxTextField.clear();
            jfxTextField.setStyle("-fx-border-color: white;" + "-fx-border-width: 1");
        }
        for (JFXTextField jfxTextField : studentMap.keySet()) {
            jfxTextField.clear();
            jfxTextField.setStyle("-fx-border-color: white;" + "-fx-border-width: 1");
        }

        txtRegistrationNo.clear();
        txtParentId.clear();
        rdBtnMale.setSelected(false);
        rdBtnFemale.setSelected(false);
        rdMale.setSelected(false);
        rdFemale.setSelected(false);
        txtProgramId.clear();
        txtRegistrationDate.clear();
        cmbDay.getSelectionModel().clearSelection();
        cmbMonth.getSelectionModel().clearSelection();
        cmbYear.getSelectionModel().clearSelection();
        cmbParentTitle.getSelectionModel().clearSelection();
        cmbPrograms.getSelectionModel().clearSelection();
    }

    public void clearFieldsOnAction(ActionEvent actionEvent) {
        clearAllFields();
    }

    public void rdBtnStMaleClick(MouseEvent mouseEvent) {
        rdBtnFemale.setSelected(false);
    }

    public void rdBtnFemaleClick(MouseEvent mouseEvent) {
        rdBtnMale.setSelected(false);
    }

    public void rdBtnPrtMaleClick(MouseEvent mouseEvent) {
        rdFemale.setSelected(false);
    }

    public void rdBtnPrtFemaleClick(MouseEvent mouseEvent) {
        rdMale.setSelected(false);
    }

    public void rdBtAddNewStudentClick(MouseEvent mouseEvent) {
        rdBtnExistingStudent.setSelected(false);
        txtRegistrationNo.setDisable(true);
        btnSearch.setDisable(true);
        cmbPrograms.setDisable(true);
        txtRegistrationDate.setDisable(true);
        txtProgramId.setDisable(true);
        btnRegisterStudent.setDisable(false);
        btnRegisterStudent.setText("Next");
    }

    public void rdBtnExistingStudentClick(MouseEvent mouseEvent) {
        rdBtnAddNewStudent.setSelected(false);
        txtRegistrationNo.setDisable(false);
        btnSearch.setDisable(false);
        cmbPrograms.setDisable(false);
        txtRegistrationDate.setDisable(false);
        txtProgramId.setDisable(false);
        btnRegisterStudent.setDisable(false);
        btnRegisterStudent.setText("Update");
    }
}

package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.business.BOFactory;
import lk.ijse.hibernate.business.custom.ProgramBO;
import lk.ijse.hibernate.business.custom.StudentRegistrationBO;
import lk.ijse.hibernate.dto.ProgramDTO;
import lk.ijse.hibernate.repository.DAOFactory;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class AddProgramsFormController {
    public AnchorPane addProgramsContext;
    public JFXTextField txtProgramName;
    public JFXTextField txtSubjectId;
    public JFXTextField txtProgramDuration;
    public JFXTextField txtProgramFee;
    public TableColumn colProgramName;
    public TableColumn colProgramFee;
    public TableView<ProgramDTO> tblProgramDetails;
    public TableColumn colProgramId;
    public TableColumn colProgramDuration;
    public Label lblDate;
    public Label lblTime;
    public JFXButton btnAddSubject;
    public Label lblTopicSubject;
    public Label lblSubjectId;
    public JFXRadioButton rdBtnAddNewSubject;

    ProgramBO programBO = (ProgramBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PROGRAM);

    LinkedHashMap<JFXTextField, Pattern> programsMap = new LinkedHashMap<>();

    Pattern programName = Pattern.compile("^([A-z0-9/,\\s]{3,})$");
    Pattern programDuration = Pattern.compile("^([A-z0-9/,\\s]{3,})$");
    Pattern programFee = Pattern.compile("^([0-9.\\s]{3,})$");


    public void initialize(){
        storeValidation();

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

        colProgramId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProgramDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colProgramFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        try {
            setProgramDetailsToTbl();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblProgramDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setTblDetailsToTextFields(newValue);
            }
        });

        for (JFXTextField jfxTextField : programsMap.keySet()) {
            jfxTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (validateProgram()){
                    btnAddSubject.setDisable(false);
                }else {
                    btnAddSubject.setDisable(true);
                }
            });
        }
    }

    private void storeValidation() {
        programsMap.put(txtProgramName,programName);
        programsMap.put(txtProgramDuration,programDuration);
        programsMap.put(txtProgramFee,programFee);
    }

    public boolean validateProgram(){
        for (JFXTextField jfxTextField : programsMap.keySet()) {
            Pattern pattern = programsMap.get(jfxTextField);
            if (!pattern.matcher(jfxTextField.getText()).matches()){
                jfxTextField.setStyle("-fx-border-color: red");
                return false;
            }else {
                jfxTextField.setStyle("-fx-border-color: green");
            }
        }
        return true;
    }

    private void setTblDetailsToTextFields(ProgramDTO newValue) {
        txtSubjectId.setText(newValue.getId());
        txtProgramName.setText(newValue.getName());
        txtProgramDuration.setText(newValue.getDuration());
        txtProgramFee.setText(newValue.getFee());

    }

    private void setProgramDetailsToTbl() throws SQLException, ClassNotFoundException {
        ObservableList<ProgramDTO> allProgramDetails = programBO.getAllProgramDetails();
        tblProgramDetails.setItems(allProgramDetails);
    }

    public void backToUserFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/UserForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) addProgramsContext.getScene().getWindow();
        window.setX(550);
        window.setY(100);
        window.setScene(new Scene(load));
    }

    public void AddSubjectOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
                ProgramDTO programDTO = new ProgramDTO(
                        txtSubjectId.getText(),txtProgramName.getText(),txtProgramDuration.getText(),txtProgramFee.getText()
                );

                ButtonType yes =new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no =new ButtonType("No", ButtonBar.ButtonData.OK_DONE);

                Alert alert =new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to add this Program ?",yes,no);
                alert.setTitle("Confirmation Alert");
                Optional<ButtonType> result = alert.showAndWait();

                //-------------------When press yes----------------------
                if(result.orElse(no)==yes){
                    if (programBO.addProgram(programDTO)){
                        ObservableList<ProgramDTO> allProgramDetails = programBO.getAllProgramDetails();
                        tblProgramDetails.setItems(allProgramDetails);
                        clearAllFields();
                        new Alert(Alert.AlertType.INFORMATION,"Program Successfully Added!").show();
                    }else{
                        new Alert(Alert.AlertType.WARNING,"'Try Again.'").show();
                    }
                }
    }

    public void updateProgramsOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ProgramDTO programDTO = new ProgramDTO(
                txtSubjectId.getText(),txtProgramName.getText(),txtProgramDuration.getText(),txtProgramFee.getText()
        );

        if (programBO.updatePrograms(programDTO)){
            ObservableList<ProgramDTO> allProgramDetails = programBO.getAllProgramDetails();
            tblProgramDetails.setItems(allProgramDetails);
            clearAllFields();
            new Alert(Alert.AlertType.INFORMATION,"Program Updated").show();
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearAllFields();
    }

    private void clearAllFields() {
        for (JFXTextField jfxTextField : programsMap.keySet()) {
            jfxTextField.clear();
            jfxTextField.setStyle("-fx-border-color: white;"+"-fx-border-width: 2;");
        }
        txtSubjectId.clear();
    }
}

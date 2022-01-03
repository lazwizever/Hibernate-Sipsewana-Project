package lk.ijse.hibernate.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserFormController {
    public AnchorPane userContext;
    public Label lblDate;
    public Label lblTime;

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
    }
    
    public void backToLoginFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) userContext.getScene().getWindow();
        window.setX(550);
        window.setY(100);
        window.setScene(new Scene(load));
    }

    public void moveToStudentregistrationOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/StudentRegistrationForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) userContext.getScene().getWindow();
        window.setX(550);
        window.setY(50);
        window.setScene(new Scene(load));

    }

    public void moveToAddProgramsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddProgramsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) userContext.getScene().getWindow();
        window.setX(200);
        window.setY(100);
        window.setScene(new Scene(load));
    }

    public void moveToPaymentOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/PaymentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) userContext.getScene().getWindow();
        window.setX(550);
        window.setY(100);
        window.setScene(new Scene(load));

    }
}

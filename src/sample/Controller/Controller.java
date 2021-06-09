package sample.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.App.AlertClass;

public class Controller {

    private AlertClass alertClass;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button enteredButton;

    @FXML
    void initialize() {
    enteredButton.setOnAction(actionEvent -> {
        if(loginTextField.getText().isEmpty()  || passwordTextField.getText().isEmpty()){
            alertClass = new AlertClass(2, "Не заполненны все поля. Пожалуйста проверьте коррекность ввода", "Недостаточно данных", "Внимание");
        } else {
        }
    });
    }
}

package sample.Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.App.AlertClass;
import sample.App.InitilizationWindow;

public class Controller {

    private final String[] negativeElements = {"\\", "/", ":", "*", "?", "<", ">", "|"};

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button enteredButton;

    @FXML
    private Button registrationButton;

    private final InitilizationWindow window = new InitilizationWindow();

    @FXML
    void initialize() {
        enteredButton.setOnAction(actionEvent -> {
            boolean check = window.checkFields(true, loginTextField.getText(), passwordTextField.getText());
            if (!check) {
                new AlertClass(2, "Не заполненны все поля. Пожалуйста введите все данные, которые от вас требуются", "Внимание", "Недостаточно данных");
            } else {
                try {
                    var fxmlLoader = window.initFxmlLoader(new FXMLLoader(), "Главное меню", "../View/app.fxml");

                } catch (IOException e) {
                    System.out.println("This warning is" + e + "\nPlease correct this warning and repeat this again");
                }
            }
        });

        registrationButton.setOnAction(actionEvent -> {
            try {
                var fxmlLoader = window.initFxmlLoader(new FXMLLoader(), "Регистрация", "../View/registration.fxml");
            } catch (IOException e) {
                System.out.println("This warning is" + e + "\nPlease correct this warning and repeat this again");
            }
        });
    }
}

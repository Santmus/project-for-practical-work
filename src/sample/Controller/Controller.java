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

public class Controller {

    private final String []negativeElements = {"\\","/", ":", "*", "?", "<", ">", "|"};

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button enteredButton;

    @FXML
    private Button registrationButton;

    @FXML
    void initialize() {
    enteredButton.setOnAction(actionEvent -> {
        if(loginTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()){
            new AlertClass(2, "Не заполненны все поля. Пожалуйста проверьте коррекность ввода", "Недостаточно данных", "Внимание");
        } else {
            checkNegativeSymbols(loginTextField.getText(), passwordTextField.getText());
            try {
                var fxmlLoader = initFxmlLoader(new FXMLLoader(), "Главное меню", "../View/app.fxml");
            } catch (IOException e) {
                System.out.println("This warning is" + e +"\nPlease correct this warning and repeat this again");
            }
        }
    });

    registrationButton.setOnAction(actionEvent -> {
        try {
            var fxmlLoader = initFxmlLoader(new FXMLLoader(), "Регистрация", "../View/registration.fxml");
        } catch (IOException e) {
            System.out.println("This warning is" + e +"\nPlease correct this warning and repeat this again");
        }
    });
    }

    private void checkNegativeSymbols(String login, String password) {}

    private Stage initStage(Parent root,  String title) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 700, 400));
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }

    private FXMLLoader initFxmlLoader(FXMLLoader loader, String title, String path) throws IOException {
        loader.setLocation(getClass().getResource(path));
        loader.load();
        Parent root = loader.getRoot();
        var stage = initStage(root, title);
        stage.show();
        return loader;
    }
}

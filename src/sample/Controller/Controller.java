package sample.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.AlertClass;
import sample.App.InitilizationWindow;
import sample.Database.ConfigsDatabase.DatabaseHandler;
import sample.Database.InformationUser.User;

public class Controller {

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
            boolean check = window.checkFields(true, loginTextField.getText().trim(), passwordTextField.getText().trim());
            boolean negativeElements = window.checkNegativeElements(loginTextField.getText(), passwordTextField.getText());

            if (!check) {
                new AlertClass(2, "Не заполненны все поля. Пожалуйста введите все данные, которые от вас требуются", "Внимание", "Недостаточно данных");
            }
            else if (!negativeElements) {
                new AlertClass(2, "Присутствуют запрещенные символы. Проверьте еще раз строку и повторите попытку", "Внимание", "Запрещенные символы");
            } else {
                getInformationOnEntered(loginTextField.getText(), passwordTextField.getText());
            }
        });

        registrationButton.setOnAction(actionEvent -> {
            initFxml("../View/registration.fxml");
        });
    }

    private void getInformationOnEntered(String login, String password){
        DatabaseHandler dbHandler = new DatabaseHandler();

        User user = new User();
        user.setLogin(loginTextField.getText());
        user.setPassword(passwordTextField.getText());

        ResultSet result = dbHandler.getUser(user);

        int counter = 0;

        try {
            while (result.next()){
                counter++;
            }
            if (counter >= 1) {
                initFxml("../View/app.fxml");
            }
        } catch (SQLException e) {
            e.getSQLState();
        }
    }

    private void initFxml(String path){
        try {
            var fxmlLoader = window.initFxmlLoader(new FXMLLoader(), "Регистрация", path);
            window.closeWindow(registrationButton);
        } catch (IOException e) {
            System.out.println("This warning is" + e + "\nPlease correct this warning and repeat this again");
        }
    }

}
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

/**
 * <p>Класс предназначен для авторизации пользователя приложения, которое использет загрузку из <b>FXML</b> файла с помощью <u><font color='red'>Scene Builder`a</font></u>.</p>
 * @author Евгений Казаченко
 * @since 1.0.2
 * @version 1.0.5 -SNAPSHOOT
 * */
public class Controller {

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button enteredButton;

    @FXML
    private Button registrationButton;

    /**
     * {@value - Создание объекта, который будет отвечать за проверку и создание нового окна}
     * */
    private final InitilizationWindow window = new InitilizationWindow();

    /**
     * Метод который инициализирует поведение программы
     * @since 1.0.2
     * */
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

    /**
     * Метод, авторизирует пользователя в системе, если он есть
     * @throws SQLException отсутсвует подключение к <b><font color = red>MySQL</font></b>, в связи с отсутсвием БД в системе
     * @param login логин пользователя
     * @param password пароль пользователя
     * @since 1.0.5
     * @see Controller#initialize()
     * */
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

    /**
     * Метод, который инициализирует загрузку <b>FXML</b> файла
     * @param path путь <b>FXML</b> файла
     * @throws IOException может возникнуть ошибка из-за неправильного пути или отсутсвие файла в системе.
     * @since 1.0.5
     * @see Controller#getInformationOnEntered(String, String)
     * */
    private void initFxml(String path){
        try {
            var fxmlLoader = window.initFxmlLoader(new FXMLLoader(), "Регистрация", path);
            window.closeWindow(registrationButton);
        } catch (IOException e) {
            System.out.println("This warning is" + e + "\nPlease correct this warning and repeat this again");
        }
    }

}
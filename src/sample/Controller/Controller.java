package sample.Controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.AlertClass;
import sample.App.InitilizationWindow;
import sample.Database.ConfigsDatabase.DatabaseHandler;
import sample.Database.InformationUser.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>Класс предназначен для авторизации пользователя приложения, которое использет загрузку из <b>FXML</b> файла с помощью <u><font color='red'>Scene Builder`a</font></u>.</p>
 * @author Евгений Казаченко
 * @since 1.0.2
 * @version 1.0.9 - SNAPSHOOT
 * */
public class Controller {

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button enteredButton;

    @FXML
    private Button registrationButton;

    @FXML
    private Label loginLabel;

    @FXML
    private Label passwordLabel;

    /**
     * {@value - Создание объекта, который будет отвечать за проверку и создание нового окна}
     * */
    private final InitilizationWindow window = new InitilizationWindow();

    /**
     * Метод, который инициализирует поведение приложения в данном окне
     * @since 1.0.2
     * */
    @FXML
    void initialize() {
        enteredButton.setOnAction(actionEvent -> {
            boolean check = window.checkFields(true, loginTextField.getText().trim(), passwordTextField.getText().trim());
            boolean negativeElements = window.checkNegativeElements(loginTextField.getText(), passwordTextField.getText());

            if (!check) {
                new AlertClass(2, "Незаполненны все поля. Пожалуйста введите все данные, которые от вас требуются и повторите попытку", "Ошибка", "Недостаточно данных");
            }
            else if (!negativeElements) {
                new AlertClass(2, "Присутствуют запрещённые символы. Проверьте введенные вами данные и повторите попытку", "Ошибка", "Запрещённые символы");
            } else {
                getInformationOnEntered();
            }
        });
        registrationButton.setOnAction(actionEvent -> window.initFxml("Регистрация","../View/registration.fxml", registrationButton));
    }

    /**
     * Метод, находит пользователя в БД <b><u><font color = red>MySQL</font></u></b>, и проводит авторизацию
     * @throws SQLException отсутсвует подключение к <b><font color = red>MySQL</font></b>, в связи с отсутсвием БД в системе.
     * @since 1.0.5
     * @see Controller#initialize()
     * */
    private void getInformationOnEntered(){
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
                window.initFxml("Главное меню","../View/main.fxml", enteredButton);
            }
            else{
                playAnimationOnButtonSignUp();
                System.err.println("Вход не выполнен, повторите попытку");
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
    }

    /** Метод вызывает анимацию из метода {@link InitilizationWindow#playAnimNode(Node)} в меню авторизации и стирает неправильные данные логина и пароля
     * @since 1.0.6
     * @see Controller#initialize()
     * */
    private void playAnimationOnButtonSignUp(){
        window.playAnimNode(loginLabel);
        window.playAnimNode(passwordLabel);

        window.playAnimNode(loginTextField);
        window.playAnimNode(passwordTextField);

        loginTextField.clear();
        passwordTextField.clear();

    }
}
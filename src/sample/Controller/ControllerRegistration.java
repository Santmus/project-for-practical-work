package sample.Controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import sample.AlertClass;
import sample.App.InitilizationWindow;
import sample.Database.ConfigsDatabase.DatabaseHandler;
import sample.Database.InformationUser.User;

/**
 * <p>Класс предназначен для регистрации пользователя приложения, которое использет загрузку из <b>FXML</b> файла с помощью <u><font color='red'>Scene Builder`a</font></u>.</p>
 * @author Евгений Казаченко
 * @since 1.0.2
 * @version 1.0.5 -SNAPSHOOT
 * */
public class ControllerRegistration {

    @FXML
    private TextField nameTextField;

    @FXML
    private Button registrationButton;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField specialCodeTextField;

    @FXML
    private RadioButton radioButtonMale;

    @FXML
    private RadioButton radioButtonFemale;

    @FXML
    private Label genderLabel;
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
        DatabaseHandler dbHandler = new DatabaseHandler();

        getRadButtonArray();

        registrationButton.setOnAction(actionEvent -> {
            {
                genderLabel.setVisible(false);
                String valueOfRadioButton = selectSection();

                User user = new User(surnameTextField.getText(), nameTextField.getText(), loginTextField.getText(), passwordTextField.getText(), valueOfRadioButton);

                boolean check = window.checkFields(true, user.getName().trim(), user.getLogin().trim(), user.getSurname().trim(), user.getPassword().trim(), specialCodeTextField.getText().trim());
                boolean negativeElements = window.checkNegativeElements(user.getName(), user.getLogin(), user.getSurname(), user.getPassword(), specialCodeTextField.getText());
                boolean checkPassword = window.checkLengthString(user.getPassword());

                if (!check) {
                    new AlertClass(2, "Незаполненны все поля. Пожалуйста введите все данные, которые от вас требуются и повторите попытку.", "Ошибка", "Недостаточно данных");
                }
                else if (!(radioButtonFemale.isSelected() || radioButtonMale.isSelected() )){
                    warningRadioButton();
                    //new AlertClass(2, "Не выбран пол сотрудника. Выберите пол и продолжите.", "Ошибка", "Не выбран пол");
                }
                else if (!negativeElements){
                    new AlertClass(2, "Присутствуют запрещенные символы. Проверьте еще раз данные и повторите попытку", "Ошибка", "Запрещенные символы");
                }
                else if (!checkPassword) {
                    /*
                    window.playAnimNode(passwordTextField);
                    passwordTextField.clear();
                    passwordTextField.setPromptText("Введите пароль не менее 8 символов");
                    */
                    new AlertClass(2, "Вы ввели слишком маленький пароль. Длина пароля должна быть не менее 8 символов.", "Ошибка", "Слишком маленький пароль");
                }
                else if (!specialCodeTextField.getText().equals("07.07.1976")){
                    /*
                    window.playAnimNode(specialCodeTextField);
                    specialCodeTextField.clear();
                    specialCodeTextField.setPromptText("Неверный специальный код");
                    */
                    new AlertClass(2, "Введен неверный специальный код при регистрации. Повторите попытку.", "Ошибка", "Не вверный код");
                }
                else {
                    dbHandler.addUserToDatabase(user);
                    new AlertClass(1, "Вы успешно зарегистрировались в приложении. Для входа в систему необходимо ввести логин и пароль вашей учетной записи", "Информация", "Успех регистрации");
                    initFxml("../View/authorization.fxml");
                }
            }
        });
    }

    private void warningRadioButton() {
        window.playAnimNode(radioButtonMale);
        window.playAnimNode(radioButtonFemale);
        window.playAnimNode(genderLabel);
        genderLabel.setVisible(true);
    }

    /**
     * Метод, который определяет значение <b>RadioButton</b>
     * @since 1.0.5
     * @see ControllerRegistration#initialize()
     * */
    private String selectSection(){
        if (radioButtonFemale.isSelected()) return "Женщина";
        else return "Мужчина";
    }

    /**
     * Метод, который инициализирует загрузку <b>FXML</b> файла
     * @param path путь <b>FXML</b> файла
     * @throws IOException ошибка из-за неправильного пути или отсутсвие файла в системе.
     * @since 1.0.5
     * @see ControllerRegistration#initialize()
     * */
    private void initFxml(String path){
        try {
            var fxmlLoader = window.initFxmlLoader(new FXMLLoader(), "Регистрация", path);
            window.closeWindow(registrationButton);
        } catch (IOException e) {
            System.err.println("This warning is" + e + "\nPlease correct this warning and repeat this again");
        }
    }

    /**
     * Метод, который группирует <b>{@link RadioButton RadioButtons}</b>
     * @since 1.0.5
     * @see ControllerRegistration#initialize()
     * */
    private void getRadButtonArray(){
        ObservableList<RadioButton> radButtonArray = FXCollections.observableArrayList();
        radButtonArray.addAll(radioButtonMale, radioButtonFemale);
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(radButtonArray);
    }
}

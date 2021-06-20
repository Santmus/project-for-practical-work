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
                String valueOfRadioButton = selectSection(radioButtonFemale);

                User user = new User(surnameTextField.getText(), nameTextField.getText(), loginTextField.getText(), passwordTextField.getText(), valueOfRadioButton);

                boolean check = window.checkFields(true, user.getName().trim(), user.getLogin().trim(), user.getSurname().trim(), user.getPassword().trim(), specialCodeTextField.getText().trim());
                boolean negativeElements = window.checkNegativeElements(user.getName(), user.getLogin(), user.getSurname(), user.getPassword(), specialCodeTextField.getText());
                boolean checkPassword = window.checkLengthString(user.getPassword());

                if (!check) {
                    new AlertClass(2, "Не заполненны все поля. Пожалуйста введите все данные, которые от вас требуются", "Внимание", "Недостаточно данных");
                }
                else if (!(radioButtonFemale.isSelected() || radioButtonMale.isSelected() )){
                    new AlertClass(2, "Не выбран пол сотрудника. Выберите пол и продолжите.", "Внимание", "Не выбран пол");
                }
                else if (!negativeElements){
                    new AlertClass(2, "Присутствуют запрещенные символы. Проверьте еще раз данные и повторите попытку", "Внимание", "Запрещенные символы");
                }
                else if (!checkPassword) {
                    new AlertClass(2, "Вы ввели слишком маленький пароль. Попробуйте еще раз.", "Внимание", "Слишком маленький пароль");
                }
                else if (!specialCodeTextField.getText().equals("07.07.1976")){
                    new AlertClass(2, "Введен неверный специальный код при регистрации. Повторите попытку. ", "Внимание", "Не вверный код");
                }
                else {
                    dbHandler.addUserToDatabase(user);
                    initFxml("../View/app.fxml");
                }
            }
        });
    }


    /**
     * Метод, который определяет значение <b>RadioButton</b>
     * @param radioButtonFemale объект женской кнопки
     * @since 1.0.5
     * @see ControllerRegistration#initialize()
     * */
    private String selectSection (RadioButton radioButtonFemale){
        if (radioButtonFemale.isSelected()) return "Женщина";
        else return "Мужчина";
    }

    /**
     * Метод, который инициализирует загрузку <b>FXML</b> файла
     * @param path путь <b>FXML</b> файла
     * @throws IOException может возникнуть ошибка из-за неправильного пути или отсутсвие файла в системе.
     * @since 1.0.5
     * @see ControllerRegistration#initialize()
     * */
    private void initFxml(String path){
        try {
            var fxmlLoader = window.initFxmlLoader(new FXMLLoader(), "Регистрация", path);
            window.closeWindow(registrationButton);
        } catch (IOException e) {
            System.out.println("This warning is" + e + "\nPlease correct this warning and repeat this again");
        }
    }

    /**
     * Метод, который группирует <b>RadioButton</b>
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

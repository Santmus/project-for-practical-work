package sample;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

/** <p>Данный класс предназначен для инициализации допольнительных окон, в которых выводится различная информация, такие как: </p>
 * <ol>
 * <li> Дополнительная информация при выполнения программы </li>
 * <li> Определенные ошибки, которые нужно исправить </li>
 * <li> Окна с определенным выбором</li>
 * </ol>
 * @author Евгений Казаченко
 * @since 1.0.0
 * @version 1.0.9 - SNAPSHOOT
 * */
public class AlertClass{

    /**
     * <p>Данный конструктор предназначен для вывода необходимого дополнительного окна.</p>
     * <p>После происходит вызова метода {@link AlertClass#getAlertInformation(Alert, String, String, String)}</p>
     * @param title заголовок, который будет использоватся для дополнительного окна
     * @param content необходимая информация, которая будет написана в дополнительном окне
     * @param header наименновие проблемы/ошибки/информации, которая будет выведенна в дополнительном окне
     * @param number номер, из-за которого будет выводится необходимое дополнительное окно
     * @since 1.0.1
     * */
    public AlertClass(int number, String content, String title, String header){
        Alert alert;
        switch (number){
            case 1:{
                alert = new Alert(Alert.AlertType.INFORMATION);
                getAlertInformation(alert, content, title, header);
                break;
            }
            case 2:{
                alert = new Alert(Alert.AlertType.WARNING);
                getAlertInformation(alert, content, title, header);
                break;
            }
            case 3:{
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                getAlertInformation(alert, content, title, header);
                break;
            }
            default:{
                System.out.println("Please add or fix this warning and repeat this function");
                break;
            }
        }
    }

    /**
     * <p>Данный конструктор предназначен для вывода дополнительного окна с выбором</p>
     * <p>После происходит вызова метода {@link AlertClass#getExitInformation(Alert, String, String, String, Stage)}</p>
     * @param title заголовок, который будет использоватся для дополнительного окна с выбором
     * @param content необходимая информация, которая будет написана в дополнительном окне с выбором
     * @param header наименновие проблемы/ошибки/информации, которая будет выведенна в дополнительном окне с выбором
     * @param stage текущее окно
     * @since 1.0.5
     * */
    public AlertClass(String content, String title, String header, Stage stage){
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        getExitInformation(alert, content, title, header, stage);
    }

    /**
     * Метод, который выводит допольнительно окно с информацией
     * @param header наименновие проблемы/ошибки/информации, которая будет написана в дополнительном окне
     * @param content необходимая информация, которая будет выведенна в дополнительном окне
     * @param title заголовок дополнительного окна
     * @param alert объект из которого создается новое дополнительное окно
     * @since 1.0.2
     * */
    private void getAlertInformation(Alert alert, String content, String title, String header){
        alert.setTitle(title);
        var stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(AlertClass.class.getResourceAsStream("Assets/logo.png"))) ;
        alert.setHeaderText(header);
        alert.setContentText(content);
        Optional<ButtonType> option = alert.showAndWait();
    }

    /**
     * Метод, который выводит допольнительно окно с выбором
     * @param header наименновие проблемы/ошибки/информации, которая будет написана в дополнительном окне с выбором
     * @param content необходимая информация, которая будет выведенна в дополнительном окне с выбором
     * @param title заголовок дополнительного окна с выбором
     * @param alert объект из которого создается новое дополнительное окно с выбором
     * @param returnStage текущее окно
     * @since 1.0.2
     * */
    private void getExitInformation(Alert alert, String content, String title, String header, Stage returnStage){
        alert.setTitle(title);
        var stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(AlertClass.class.getResourceAsStream("Assets/logo.png"))) ;
        alert.setContentText(content);
        alert.setHeaderText(content);
        ButtonType exitProgramButton = new ButtonType("Завершить");
        ButtonType returnProgramButton = new ButtonType("Отмена");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(returnProgramButton, exitProgramButton);
        Optional<ButtonType> option = alert.showAndWait();
        option.get();
        // fix return window
        if (option.get() == returnProgramButton) {

        } else if (option.get() == exitProgramButton) {
            System.out.println("Program finish work");
            Platform.exit();
        }
    }
}

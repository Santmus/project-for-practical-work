package sample;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.App.InitilizationWindow;

import java.util.Optional;

/** <p>Данный класс предназначен для инициализации допольнительных окон, в которых выводится различная информация, такие как: </p>
 * <ol>
 * <li> Дополнительная информация при выполнении программы </li>
 * <li> Предупреждения/ошибки, которые нужно исправить, или обратить на их анимание </li>
 * <li> Окна при заверщение работы</li>
 * </ol>
 * @author Евгений Казаченко
 * @since 1.0.0
 * @version 1.0.12 - SNAPSHOOT
 * */
public class AlertClass{

    private final InitilizationWindow initilizationWindow = new InitilizationWindow();

    /**
     * <p>Данный конструктор предназначен для вывода необходимого дополнительного окна.</p>
     * <p>После происходит вызова метода {@link AlertClass#getAlertInformation(Alert, String, String, String)}</p>
     * @param title заголовок, который будет использоватся для дополнительного окна
     * @param content необходимая информация, которая будет выведена в дополнительном окне
     * @param header наименновие проблемы/ошибки/информации, которая будет выведена в дополнительном окне
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
     * <p>Данный конструктор предназначен для вывода завершающего окна</p>
     * <p>После происходит вызова метода {@link AlertClass#getExitInformation(Alert, String, String, String, Parent)}</p>
     * @param title заголовок, который будет использоватся для завершающего окна
     * @param content необходимая информация, которая будет выведена в завершающем окне
     * @param root объект, образующий новое окно
     * @since 1.0.5
     * */
    public AlertClass(String content, String title, String header, Parent root){
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        getExitInformation(alert, content, title, header, root);
    }

    /**
     * Метод, который выводит дополнительное окно с информацией
     * @param header наименнование проблемы/ошибки/информации, которая будет выведена в дополнительном окне
     * @param content необходимая информация, которая будет выведена в дополнительном окне
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
     * Метод, который выводит завершающее окно
     * @param alert объект из которого создается завершающее окно
     * @param content необходимая информация, которая будет выведена в завершающем окне
     * @param title заголовок в завершающем окне
     * @param root объект, образующий новое окно
     * @since 1.0.2
     * */
    private void getExitInformation(Alert alert, String content, String title, String header, Parent root){
        alert.setTitle(title);
        var stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(AlertClass.class.getResourceAsStream("Assets/logo.png"))) ;
        alert.setContentText(content);
        alert.setHeaderText(header);
        ButtonType exitProgramButton = new ButtonType("Завершить");
        ButtonType returnProgramButton = new ButtonType("Отмена");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(returnProgramButton, exitProgramButton);
        Optional<ButtonType> option = alert.showAndWait();
        option.get();

       //solver problem "do work but it void error:AnchorPane@6b2dc020[styleClass=root]is already set as root of another scene"
        if (option.get() == returnProgramButton) {
            initilizationWindow.initStage(root, title);
        } else if (option.get() == exitProgramButton) {
            System.out.println("Program finish work");
            Platform.exit();
        }
    }
}

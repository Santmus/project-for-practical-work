package sample;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @author Евгений
 *
 * */
public class AlertClass{

    public AlertClass(int number, String content, String title, String header){
        Alert alert;
        if (number == 1){
            alert = new Alert(Alert.AlertType.INFORMATION);
            getAlertInformation(alert, content, title, header);
        }
        else if (number == 2)
        {
            alert = new Alert(Alert.AlertType.WARNING);
            getAlertInformation(alert, content, title, header);
        }
        else if (number == 3){
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            getAlertInformation(alert, content, title, header);
        }
    }

    public AlertClass(String content, String title, String header, Stage stage){
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        getExitInformation(alert, content, title, header, stage);
    }



    private void getAlertInformation(Alert alert, String content, String title, String header){
        alert.setTitle(title);
        var stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(AlertClass.class.getResourceAsStream("Assets/logo.png"))) ;
        alert.setHeaderText(header);
        alert.setContentText(content);
        Optional<ButtonType> option = alert.showAndWait();
    }
    
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

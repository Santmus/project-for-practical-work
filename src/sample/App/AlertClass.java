package sample.App;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertClass{

    public AlertClass(int number, String content, String title, String header){
        Alert alert;
        if (number == 1){
            alert = new Alert(Alert.AlertType.INFORMATION);
            setAlertInformation(alert, content, title, header);
        }
        else if (number == 2)
        {
            alert = new Alert(Alert.AlertType.WARNING);
            setAlertInformation(alert, content, title, header);
        }
        else if (number == 3){
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            setAlertInformation(alert, content, title, header);
        }
    }

    private void setAlertInformation(Alert alert, String content, String title, String header){
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Optional<ButtonType> option = alert.showAndWait();
    }
}

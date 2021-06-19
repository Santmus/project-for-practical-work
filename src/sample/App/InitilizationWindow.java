package sample.App;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.AlertClass;

import java.io.IOException;

public class InitilizationWindow {

    private final String[] negativeElements = {"\\", "/", ":", "*", "?", "<", ">", "|", " "};

    private Stage initStage(Parent root, String title) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 700, 400));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(InitilizationWindow.class.getResourceAsStream("../Assets/logo.png")));
        closeStage(stage);
        return stage;
    }

    private void closeStage(Stage stage){
        stage.setOnCloseRequest(event -> {
        new AlertClass("Вы точно хотите выйти из программы?","ООО \"Евросетка\"","Выход из приложения", stage);
        });
    }

    public FXMLLoader initFxmlLoader(FXMLLoader loader, String title, String path) throws IOException {
        loader.setLocation(getClass().getResource(path));
        loader.load();
        Parent root = loader.getRoot();
        var stage = initStage(root, title);
        stage.show();
        return loader;
    }

    public boolean checkFields (boolean value, String ...strings){
        for (String t: strings) {
            if (t.isEmpty()) {
                value = false;
                break;
            }
        }
    return value;
    }

    public boolean checkNegativeElements(String ...strings){
        for (String str:strings) {
            for (int i = 0; i < str.length(); i++){
                char x = str.charAt(i);
                String charToString = String.valueOf(x);
                for (int j = 0; j < negativeElements.length; j++){
                    if (charToString.equals(negativeElements[j])){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkLengthString (String string){
        if (string.length() < 8){
        return false;
        }
        return true;
    }

    public void closeWindow(Button button){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

}


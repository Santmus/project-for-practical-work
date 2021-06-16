package sample.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class App extends Application {

    private final InitilizationWindow window = new InitilizationWindow();

    @Override
    public void start(Stage primaryStage) throws Exception{
        var fxmlLoader = window.initFxmlLoader(new FXMLLoader(), "ООО \"Евросетка\"", "../View/authorization.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

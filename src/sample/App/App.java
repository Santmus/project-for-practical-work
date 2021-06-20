package sample.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
/**
 * Данный класс содержит методы для запуска приложения на платформе <b>JavaFX</b>
 * @author Евгений Казаченко
 * @since 1.0.0
 * @version 1.0.5 - SNAPSHOOT
 * */

public class App extends Application {

    private final InitilizationWindow window = new InitilizationWindow();

    /**
     * Данный метод запускает стартовое окно приложения
     * @param primaryStage первоначальное окно приложения
     * @throws Exception может возникнуть ошибка из-за неправильного пути или отсутсвие файла в системе.
     * Файл должен находится в репозитории:<u>/sample/View</u></p>
     * */
    @Override
    public void start(Stage primaryStage) throws Exception{
        var fxmlLoader = window.initFxmlLoader(new FXMLLoader(), "ООО \"Евросетка\"", "../View/authorization.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

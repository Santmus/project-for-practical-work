package sample.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
/**
 * Главный класс, который создает основной поток <b>App</b>
 * и предназначен для запуска настольного приложения разработанного на базе <b>JavaFX</b>
 * @author Евгений Казаченко
 * @since 1.0.0
 * @version 1.0.12 - SNAPSHOOT
 * */

public class App extends Application {

    private final InitilizationWindow window = new InitilizationWindow();

    /**
     * Данный метод запускает стартовое окно приложения
     * @param primaryStage первоначальное окно приложения
     * @throws Exception ошибка из-за неправильного пути или отсутствие файла в системе.
     * Файл должен находится в репозитории:<u>/sample/View</u></p>
     * */
    @Override
    public void start(Stage primaryStage) throws Exception{
        window.initFxmlLoader(new FXMLLoader(), "ООО \"Евросетка\"", "../View/authorization.fxml");
    }

    /**
     * Метод, в котором происходит запуск основного потока
     * @since 1.0.0
     * */
    public static void main(String[] args) {
        launch(args);
    }
}

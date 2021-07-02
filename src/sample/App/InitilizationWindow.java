package sample.App;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.AlertClass;
import sample.Animations.Shake;
import sample.Controller.Controller;
import sample.Controller.ControllerRegistration;

import java.io.IOException;

/**
 * <p>Данный класс предназначен для создания окон приложения и проверки полей при инициализации и авторизации пользователей в системе</p>
 * <p>Пример: {@link InitilizationWindow#checkFields(boolean, String...)} - проверка полей </p>
 * @author Евгений Казаченко
 * @since 1.0.1
 * @version 1.0.12 - SNAPSHOOT
 * */
public class InitilizationWindow {

    /**
     * {@value - нежелательные элементы при авторизации и регистрации пользователя}
     * */
    private final String[] negativeElements = {"\\", "/", ":", "*", "?", "<", ">", "|", " "};

    private Shake shake;

    /**
     * Метод, который инициализирует новое окно и возращает его
     * @since 1.0.1
     * @param root объект, образующий новое окно
     * @param title заголовок для нового окна
     * @return новое окно
     * @see InitilizationWindow#initStage(Parent, String)
     * */
    public Stage initStage(Parent root, String title) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(InitilizationWindow.class.getResourceAsStream("../Assets/logo.png")));

        closeStage(stage, root);

        return stage;
    }

    /**
     * Метод предназначен для активации скрипта - закрытии окна, при переходе к другому окну
     * @param stage нынешнее окно приложения
     * @param root
     * @since 1.0.1
     * @see InitilizationWindow#initStage(Parent, String)
     * */
    private void closeStage(Stage stage, Parent root){
        stage.setOnCloseRequest(event -> {
        new AlertClass("Вы точно хотите выйти из программы?","ООО \"Евросетка\"","Выход из приложения",  root);
        });
    }

    /**
     * <p>Метод предназначенный для загрузки <b>FXML</b> файла, находящегося в репозирории:<u>/sample/View</u></p>
     * @since 1.0.1
     * @param title заголовок для нового окна
     * @param loader загрузка необходимого <b>FXML</b> файла
     * @param path путь к нужному <b>FXML</b> файлу. Путь относительный
     * @return возращает загрузчик <b>FXML</b> файла
     * */
    public FXMLLoader initFxmlLoader(FXMLLoader loader, String title, String path) throws IOException {
        loader.setLocation(getClass().getResource(path));
        loader.load();
        Parent root = loader.getRoot();
        var stage = initStage(root, title);
        stage.show();
        return loader;
    }

    /**
     * Метод предназначен для проверки пустых значений в введенных полях
     * @param strings <b>varargs</b> String-ов для проверки, введенны ли значения в поля
     * @param value предназначен для определения, является ли поля пустым, или нет
     * @since 1.0.2
     * @see Controller#initialize()
     * @see ControllerRegistration#initialize()
     * @return возращает <b>true</b> если все поля заполненны, иначе <b>false</b>
     **/
    public boolean checkFields (boolean value, String ...strings){
        for (String t: strings) {
            if (t.isEmpty()) {
                value = false;

                break;
            }
        }
    return value;
    }

    /**
     * Метод предназначен для проверки запрещенных символов в введенных полях
     * @param strings <b>varargs</b> String-ов запрещенных элементов в полях
     * @since 1.0.3
     * @see Controller#initialize()
     * @see ControllerRegistration#initialize()
     * @return возращает <b>true</b> если нет запрещенных элементов в введенных полях, иначе <b>false</b>
     **/
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

    /**
     * Метод предназначен для проверки количество введенных символов при регистрации пользователя, а именно пароля
     * @param string значение поля пароля
     * @since 1.0.3
     * @see ControllerRegistration#initialize()
     * @return возращает <b>true</b> если введенный пароль больше или равно 8, иначе <b>false</b>
     **/
    public boolean checkLengthString (String string){
        if (string.length() < 8){
            return false;
        } else return true;
    }

    /**
     * Метод предназначен для закрытия предыдущего окна, при переходе к другому окну
     * @param button кнопка, из которой происходит переход к другому окну приложения
     * @since 1.0.1
     * @see InitilizationWindow#initFxml(String, String, Button)*
     * */
    public void closeWindow(Button button){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    /**
     * Метод, вызывает проигрывание анимации в результате неправильной инициализации пользователя
     * @since 1.0.6
     * @see Controller#playAnimationOnButtonSignUp()
     * */
    public void playAnimNode(Node node){
        Shake anim = new Shake(node);
        anim.playAnim();
    }

    /**
     * Метод, который инициализирует загрузку <b>FXML</b> файла
     * @param path путь <b>FXML</b> файла
     * @throws IOException ошибка из-за неправильного пути или отсутсвие файла в системе.
     * @since 1.0.5
     * @see ControllerRegistration#initialize()
     * @see Controller#initialize()
     * */
    public void initFxml(String title, String path, Button button){
        try {
            var fxmlLoader = initFxmlLoader(new FXMLLoader(), title, path);
             closeWindow(button);
        } catch (IOException e) {
            System.err.println("This warning is" + e + "\nPlease correct this warning and repeat this again");
        }
    }
}


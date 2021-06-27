package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import sample.App.InitilizationWindow;
import sample.Parsers.BankCourseParser;


/*
 Добавить:
 1. Обновление главного меню при выборе валюты
 2. Дополнительное окно потверждающее выбор необходимой валюты
 * */
public class ControllerChooseConverter {

    @FXML
    private ComboBox<String> chooseConvertComboBox;

    @FXML
    private Button acceptButton;

    @FXML
    private Button backMainMenuButton;

    private final InitilizationWindow initilizationWindow = new InitilizationWindow();

    @FXML
    void initialize() {
        initComboBox();

        backMainMenuButton.setOnAction(actionEvent2 -> {
            System.out.println("Back to main menu is action");
            initilizationWindow.initFxml("Главное меню","../View/main.fxml", backMainMenuButton);
        });
    }

    private double currencyСounting(String abreviation) {
        BankCourseParser bankCourseParser = new BankCourseParser();
        return bankCourseParser.chooseCourse(abreviation);
    }

    private void initComboBox() {
        ObservableList<String> covert = FXCollections.observableArrayList("Доллары США (USD)",
                "Российские рубли (RUS)", "Белорусские рубли (BYN)");
        chooseConvertComboBox.setItems(covert);

        chooseConvertComboBox.setOnAction(actionEvent -> {
            double currency;
            if (chooseConvertComboBox.getValue().equals("Доллары США (USD)")){
                currency = currencyСounting("USD");
                acceptButton.setOnAction(actionEvent1 -> System.out.println("This currency if I accept button is: " + currency));
            }
            else if (chooseConvertComboBox.getValue().equals("Российские рубли (RUS)")){
                currency = currencyСounting("RUB");
                acceptButton.setOnAction(actionEvent1 -> System.out.println("This currency if I accept button is: " + currency));
            }
            else {
                currency = currencyСounting("BYN");
                System.out.println(currency);
                acceptButton.setOnAction(actionEvent1 -> System.out.println("This currency if I accept button is: " + currency));
            }
        });
    }
}


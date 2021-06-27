package sample.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import sample.App.InitilizationWindow;


/**
 * <p>Класс предназначен для работы с основной логикой приложение</p>
 * <p>Онсовные аспекты данного класса</p>
 * @author Евгений Казаченко
 * @since 1.0.6
 * @version 1.0.9 - SNAPSHOOT
 * */
public class AppController {

    @FXML
    private AnchorPane windowAnchorPane;

    @FXML
    private TableView<?> productTableView;

    @FXML
    private TableColumn<?, ?> nameOfProductTableColumn;

    @FXML
    private TableColumn<?, ?> countOfProductTableColumn;

    @FXML
    private TableColumn<?, ?> companyTableColumn;

    @FXML
    private TableColumn<?, ?> costTableColumn;

    @FXML
    private Button nextButton;

    @FXML
    private Button previousButton;

    @FXML
    private Button lastPageButton;

    @FXML
    private Button firstPageButton;

    @FXML
    private Button loadDatailTableViewButton;

    @FXML
    private Button chooseConvertationButton;

    @FXML
    private Button upgradeTableViewButton;

    @FXML
    private Button courseExchangeValuesButton;

    private final InitilizationWindow window = new InitilizationWindow();

    @FXML
    void initialize() {

        nextButton.setOnAction(actionEvent -> {
            System.out.println("Next button is action");
        });
        previousButton.setOnAction(actionEvent -> {
            System.out.println("Previous button is action");
        });
        lastPageButton.setOnAction(actionEvent -> {
            System.out.println("Last page button is action");
        });
        firstPageButton.setOnAction(actionEvent -> {
            System.out.println("First page button is action");
        });

        loadDatailTableViewButton.setOnAction(actionEvent -> {
            System.out.println("load detail button is action");
        });
        upgradeTableViewButton.setOnAction(actionEvent -> {
            System.out.println("Upgrade table button is action");
        });

        chooseConvertationButton.setOnAction(actionEvent -> {
            System.out.println("Choose convertation button is action");
            window.initFxml("Выбор валюты", "../View/chooseValueConvertation.fxml", chooseConvertationButton);
        }
        );
        courseExchangeValuesButton.setOnAction(actionEvent -> {
            System.out.println("Course exchange button is action");
            window.initFxml("Курс валют", "../View/exchangeRates.fxml", courseExchangeValuesButton);
        });
    }



}

package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sample.App.InitilizationWindow;
import sample.Parsers.Data.Product;
import sample.Parsers.HtmlSiteParser;


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
    public TableView<Product> productTableView;

    public ObservableList<Product> productData = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Product, String> nameOfProductTableColumn;

    @FXML
    private TableColumn<Product, String> sizeOfProductTableColumn;

    @FXML
    private TableColumn<Product, String> brandTableColumn;

    @FXML
    private TableColumn<Product, String> regionTableColumn;

    @FXML
    private TableColumn<Product, String> costTableColumn;

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

        //25 %
        loadDatailTableViewButton.setOnAction(actionEvent -> {
            System.out.println("load detail button is action");

            nameOfProductTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("nameOfProduct"));
            sizeOfProductTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("sizeOfProduct"));
            brandTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
            regionTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("region"));
            costTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("cost"));

            HtmlSiteParser htmlSiteParser = new HtmlSiteParser();
            htmlSiteParser.loadDataToHTML(0, productTableView, productData);

        });

        // 85 %
        chooseConvertationButton.setOnAction(actionEvent -> {
            System.out.println("Choose convertation button is action");
            window.initFxml("Выбор валюты", "../View/chooseValueConvertation.fxml", chooseConvertationButton);
        }
        );

        // complete
        courseExchangeValuesButton.setOnAction(actionEvent -> {
            System.out.println("Course exchange button is action");
            window.initFxml("Курс валют", "../View/exchangeRates.fxml", courseExchangeValuesButton);
        });
    }



}

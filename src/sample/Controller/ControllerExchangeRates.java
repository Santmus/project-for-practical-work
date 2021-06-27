package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.App.InitilizationWindow;
import sample.Parsers.BankCourseParser;
import sample.Parsers.Data.DataCourse;

public class ControllerExchangeRates {

    @FXML
    private Button backToMainMenuButton;

    @FXML
    public TableView<DataCourse> exchangeRatesTableView;

    @FXML
    public TableColumn<DataCourse, String> nameExchangeTableColumn;

    @FXML
    public TableColumn<DataCourse, String> abbreviationTableColumn;

    @FXML
    public TableColumn<DataCourse, Double> priceTableColumn;

    @FXML
    private Label informationLabel;

    @FXML
    private Hyperlink informationHyperText;

    public ObservableList<DataCourse> courseData = FXCollections.observableArrayList();

    private final InitilizationWindow initilizationWindow = new InitilizationWindow();

    @FXML
    void initialize() {

        nameExchangeTableColumn.setCellValueFactory(new PropertyValueFactory<DataCourse,String>("nameExchange"));
        abbreviationTableColumn.setCellValueFactory(new PropertyValueFactory<DataCourse,String>("abbreviation"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<DataCourse,Double>("price"));

        BankCourseParser bankCourseParser = new BankCourseParser();
        bankCourseParser.parseJSON(courseData, exchangeRatesTableView);

        backToMainMenuButton.setOnAction(actionEvent2 -> {
            System.out.println("Back to main menu is action");
            initilizationWindow.initFxml("Главное меню","../View/main.fxml", backToMainMenuButton);
        });
    }
}
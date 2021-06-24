package sample.Controller;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * <p>Класс предназначен для работы с основной логикой приложение</p>
 * <p>Онсовные аспекты данного класса</p>
 * @author Евгений Казаченко
 * @since 1.0.6
 * @version 1.0.5 -SNAPSHOOT
 * */
public class AppController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Button upgradeButton;

    @FXML
    private ComboBox<?> chooseCurrencyComboBox;

    @FXML
    private Button turnOnDiagramButton;

    @FXML
    private Button convertationButton;


    @FXML
    void initialize() {

    }
}

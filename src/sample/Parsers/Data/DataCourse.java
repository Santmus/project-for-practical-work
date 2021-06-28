package sample.Parsers.Data;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Класс предназначенный для записи данных в таблицу курса валют НБ РБ
 * @author Евгений Казаченко
 * @since 1.0.8
 * @version 1.0.11 - SNAPSHOOT
 * */
public class DataCourse {

    private String nameExchange;
    private String abbreviation;
    private Double price;
/**
 * Конструктор, для иниицализации поля
 * @param abbreviation сокращенное название валюты
 * @param price стоимость валюты по отношение к белорусскому рублю
 * @param nameExchange название валюты
 * @see sample.Parsers.BankCourseParser#parseJSON(ObservableList, TableView)
 * */
    public DataCourse(String nameExchange, String abbreviation, Double price){
        this.nameExchange = nameExchange;
        this.abbreviation = abbreviation;
        this.price = price;
    }
    public double getPrice() {
        return price;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getNameExchange() {
        return nameExchange;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setNameExchange(String nameExchange) {
        this.nameExchange = nameExchange;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

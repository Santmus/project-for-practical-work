package sample.Price;

/**
 * Класс предназначен для высчитывании суммы товара, который мы хотим <b>изучить/купить</b>
 * @author Евгений Казаченко
 * @since 1.0.8
 * @version 1.0.9 - SNAPSHOOT
 * */
public class Price {

    /**
     * Метод, предназначенный для определенния суммы для покупки
     * @param count количество покупамого товара
     * @param price стоимость в оригинальной валюте
     * @param course курс валюты
     * @return возращает сумму товара
     * */
    public static double sum(int count, double course, double price){
        return (count * price) / course;
    }

}

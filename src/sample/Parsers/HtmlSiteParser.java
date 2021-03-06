package sample.Parsers;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sample.Parsers.Data.Product;

import java.io.IOException;
import java.util.Arrays;

public class HtmlSiteParser {

    public void loadDataToHTML(int numberOfSiteToParsing, TableView<Product> productTableView, ObservableList<Product> productData) {
        while (numberOfSiteToParsing < 6) {
            switch (numberOfSiteToParsing) {
                case 0 -> {
                    String name = "Лист гк сталь 3 толщина 1,5-40мм" ;
                    System.out.println("Лист гк сталь 3 толщина 1,5-40мм");
                    parserHTML("https://mc.ru/metalloprokat/stal_listovaya_g_k_st3/r1/1.5,2,2.5,3,4,5,6,7,8,9,10,12,14,16,18,20,22,25,30,32,35,36,40/mark/st3/PageAll/1", "МеталлСервис", productTableView, productData, name);
                    numberOfSiteToParsing++;
                }
                case 1 -> {
                    String name = "Лист гк (низколегированный) стал 09г2с толщина 4-20мм" ;
                    System.out.println("Лист гк (низколегированный) стал 09г2с толщина 4-20мм");
                    parserHTML("https://mc.ru/metalloprokat/list_g_k_nizkolegirovannyj/r1/4,5,6,7,8,10,12,14,16,18,20/mark/09g2s/PageAll/1", "МеталлСервис", productTableView, productData, name);
                    numberOfSiteToParsing++;
                }
                case 2 -> {
                    String name = "Лист хк сталь 08 толщина 07-3мм";
                    System.out.println("Лист хк сталь 08 толщина 07-3мм");
                    parserHTML("https://mc.ru/metalloprokat/stal_listovaya_h_k_st/r1/0.7,0.8,0.9,1,1.2,1.4,1.5,1.8,2,2.5,3/mark/st08/filtr/cat", "МеталлСервис", productTableView, productData, name);
                    numberOfSiteToParsing++;
                }
                case 3 -> {
                    String name = "Лист оцинкованный толщина 0,55-2мм";
                    System.out.println("Лист оцинкованный толщина 0,55-2мм");
                    parserHTML("https://mc.ru/metalloprokat/stal_listovaya_ocinkovannaya/r1/0.55,0.7,0.8,0.9,1,1.2,1.5,2,3", "МеталлСервис", productTableView, productData, name);
                    numberOfSiteToParsing++;
                }
                case 4 -> {
                    String name = "Уголок равнополочный сталь 3 ширина полки 25-100мм";
                    System.out.println("Уголок равнополочный сталь 3 ширина полки 25-100мм");
                    parserHTML("https://mc.ru/metalloprokat/ugolok_ravnopolochnyj/r1/25,32,35,40,45,50,63,70,75,80,90,100/mark/st3/PageAll/1", "МеталлСервис", productTableView, productData, name);
                    numberOfSiteToParsing++;
                }
                case 5 -> {
                    String name = "Арматура рифленая марка А 500 ф 8-32мм";
                    System.out.println("Арматура рифленая марка А 500 ф 8-32мм");
                    parserHTML("https://mc.ru/metalloprokat/armatura_riflenaya_a3/r1/8,10,12,14,16,18,20,22,25,28,32/mark/a500s/filtr/cat", "МеталлСервис", productTableView, productData, name);
                    numberOfSiteToParsing++;
                }
            }
        }
    }

    private void parserHTML(String url, String site, TableView<Product> productTableView, ObservableList<Product> productData, String name) {
        System.out.println("Данные взяты из сайта: " + site + "\n");
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.err.println("This url " + url + " doesn`t find");
        }
        assert document != null;
        Elements elements = document.select("tbody#grid_tab");

        for (Element element : elements.select("tr")) {
            System.out.println(element.text());
            String str = element.text();
            sprittingParts(productTableView, productData, str, name);
        }
        System.out.println("\n");
    }

    private void sprittingParts(TableView<Product> productTableView, ObservableList<Product> productData, String line, String name){
        String[] subStr;
        subStr = line.split("\\s");

        System.out.println(subStr.length);
        int number = subStr.length;
        System.out.println(Arrays.toString(subStr));
        chooseNumberElementToAddTable(productTableView, productData, number, subStr, name);
    }

    private void chooseNumberElementToAddTable(TableView<Product> productTableView, ObservableList<Product> productData, int number, String[] subStr, String name) {
        switch (number) {
            case 14:
                if (name.equals("Лист гк сталь 3 толщина 1,5-40мм")) {
                    String nameOfProduct = subStr[0] + " " + subStr[1] + " " + subStr[2];
                    String size = subStr[3];
                    String brand = subStr[4];
                    String region = subStr[5];
                    String price = subStr[6] + " " + subStr[7];
                    productData.add(new Product(nameOfProduct.substring(0, nameOfProduct.length() - 1), size, brand, region, price));
                    productTableView.setItems(productData);
                } else if (name.equals("Уголок равнополочный сталь 3 ширина полки 25-100мм")) {
                    String nameOfProduct = subStr[0] + " " + subStr[1];
                    String size = subStr[2];
                    String brand = subStr[3];
                    String region = subStr[5];
                    String price = subStr[6] + " " + subStr[7];
                    productData.add(new Product(nameOfProduct.substring(0, nameOfProduct.length() - 1), size, brand, region, price));
                    productTableView.setItems(productData);
                }
                break;
        }
    }
}

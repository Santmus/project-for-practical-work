package sample.Parsers;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.Controller.ControllerExchangeRates;
import sample.Parsers.Data.DataCourse;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Класс предназначен для прасинга сайта <a href=https://www.nbrb.by/api/exrates/rates?periodicity=0><b>НБ РБ</b></a>
 * для получение актуального курса валют по отношению к белорусскому рублю
 * @author Евгений Казаченко
 * @since 1.0.9
 * @version 1.0.9 - SNAPSHOOT
 *  */
public class BankCourseParser {

    /**
     * Метод, который запускает процесс скачивание <b><u>JSON</u></b> файла
     * @throws IOException происходит из-за отсутсвие ссылки в интернете, либо отсутствие интернет-подключения
     * @since 1.0.9
     * */
    public void downloadJSON(){
        String url = "https://www.nbrb.by/api/exrates/rates?periodicity=0";
        try {
            downloadUsingNIO(url);
             } catch (IOException e) {
            System.err.println("This url " + url + "is doesn`t find");
            e.printStackTrace();
        }
    }

    /**
     * Метод, который скачивает сам непосредственно файл
     * @since 1.0.9
     * @see BankCourseParser#downloadJSON()
     * */
    private void downloadUsingNIO(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream("src/sample/Price/course.json");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        System.out.println("File dowload");
        fos.close();
        rbc.close();
    }

    /**
     * Метод, предназначен для парсинга <b><u>JSON</u></b> файла для получения курса валют
     * @since 1.0.9
     * @see BankCourseParser#downloadUsingNIO(String)
     * @throws FileNotFoundException происходит из-за отсутсвия файла в директории
     * @throws java.text.ParseException происходит из-за проблем парсинга <b><u>JSON</u></b> файла
     * @throws IOException глобальная ошибка
     * @param courseData столбик в которой содержитя данные курса валют
     * @param exchangeRatesTableView таблица в которой содержится курс валют
     * @see ControllerExchangeRates#initialize()
     * */
    public void parseJSON(ObservableList<DataCourse> courseData, TableView<DataCourse> exchangeRatesTableView){
        try {
            downloadJSON();
            FileReader fileReader = new FileReader("src/sample/Price/course.json");

            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(fileReader);

            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;

                String cur_Name = (String) jsonObject.get("Cur_Name");
                System.out.println("The course name is: " + cur_Name);

                double cur_OfficialRate = (double) jsonObject.get("Cur_OfficialRate");
                System.out.println("The official rate is: " + cur_OfficialRate);

                String cur_abbreviation = (String) jsonObject.get("Cur_Abbreviation");
                System.out.println("The abbreviation is: " + cur_abbreviation);

                courseData.add(new DataCourse(cur_Name,cur_abbreviation,cur_OfficialRate));
                exchangeRatesTableView.setItems(courseData);
                System.out.println("\n");

            }
        } catch (FileNotFoundException e) {
            System.err.println("This file doesn`t find");
        } catch (ParseException e) {
            System.err.println("Parser doesn`t work");
        } catch (IOException e) {
            System.err.println("This " + e);
            e.printStackTrace();
        }
    }


    /**
     * Метод, предназначенный для получение необходимой валюты при парсинге <b><u>JSON</u></b> файла
     * @since 1.0.9
     * @param abbreviation название необходимой валюты, которую мы хотим конвертировать
     * @return возращаем валюту, иначе получаем валюту в бел.рублях
     * @throws FileNotFoundException происходит из-за отсутсвия файла в директории
     * @throws java.text.ParseException происходит из-за проблем парсинга <b><u>JSON</u></b> файла
     * @throws IOException глобальная ошибка
     * @see sample.Controller.ControllerChooseConverter#informationWindow(String, double)
     * */
    public double chooseCourse(String abbreviation)  {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("src/sample/Price/course.json");
        } catch (FileNotFoundException e) {
            System.err.println("This file doesn`t find");
            try {
                downloadUsingNIO( "https://www.nbrb.by/api/exrates/rates?periodicity=0");
            } catch (IOException ioException) {
                System.err.println("This url https://www.nbrb.by/api/exrates/rates?periodicity=0 is doesn`t find");
                ioException.printStackTrace();
            }
            finally {
                chooseCourse(abbreviation);
            }
        }

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) jsonParser.parse(fileReader);
        } catch (IOException e) {
            System.err.println("This " + e);
            e.printStackTrace();
        } catch (ParseException e) {
            System.err.println("Parser doesn`t work");
        }
        assert jsonArray != null;
        for (Object o : jsonArray) {

            JSONObject jsonObject = (JSONObject) o;

            double cur_OfficialRate = (double) jsonObject.get("Cur_OfficialRate");
            String cur_abbreviation = (String) jsonObject.get("Cur_Abbreviation");


            if (cur_abbreviation.equals(abbreviation)){
                System.out.println(cur_OfficialRate);
                return cur_OfficialRate;
            }
        }
        return 1.0000;
    }
}

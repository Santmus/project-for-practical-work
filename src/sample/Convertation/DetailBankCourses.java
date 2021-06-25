package sample.Convertation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DetailBankCourses {

    public static void main(String[] args) {
        downloadJSON();
    }

    private static void downloadJSON(){
        String url = "https://www.nbrb.by/api/exrates/rates?periodicity=0";
        try {
            downloadUsingNIO(url);
             } catch (IOException e) {
            System.err.println("This url" + url + "is doesn`t find");
            e.printStackTrace();
        }
    }

    private static void downloadUsingNIO(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream("src/sample/Convertation/course.json");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        System.out.println("File dowload");
        fos.close();
        rbc.close();
        parseJSON();
    }

    private static void parseJSON(){
        try {
            FileReader fileReader = new FileReader("src/sample/Convertation/course.json");

            org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(fileReader);

            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;

                String cur_Name = (String) jsonObject.get("Cur_Name");
                System.out.println("The course name is: " + cur_Name);

                double cur_OfficialRate = (double) jsonObject.get("Cur_OfficialRate");
                System.out.println("The official rate is: " + cur_OfficialRate);

                String cur_abbreviation = (String) jsonObject.get("Cur_Abbreviation");
                System.out.println("The abbreviation is: " + cur_abbreviation);
                System.out.println("\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("This file doesn`t find");
        } catch (IOException e) {
            System.err.println("This " + e);
            e.printStackTrace();
        } catch (ParseException e) {
            System.err.println("Parser doesn`t work");
        }
    }
}

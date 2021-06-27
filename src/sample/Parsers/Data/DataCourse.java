package sample.Parsers.Data;

public class DataCourse {

    private String nameExchange;
    private String abbreviation;
    private Double price;

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

package sample.Parsers.Data;

public class Product {

    private String nameOfProduct;
    private String sizeOfProduct;
    private String brand;
    private String region;
    private String cost;

    public Product(String nameOfProduct, String sizeOfProduct, String brand, String region, String cost) {
        this.nameOfProduct = nameOfProduct;
        this.sizeOfProduct = sizeOfProduct;
        this.brand = brand;
        this.region = region;
        this.cost = cost;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public String getSizeOfProduct() {
        return sizeOfProduct;
    }

    public void setSizeOfProduct(String sizeOfProduct) {
        this.sizeOfProduct = sizeOfProduct;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}


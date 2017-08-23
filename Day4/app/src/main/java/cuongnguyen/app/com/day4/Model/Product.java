package cuongnguyen.app.com.day4.Model;

/**
 * Created by CuongNguyen on 07/10/17.
 */

public class Product {
    private String name;
    private int image;
    private int price;
    private int sale;

    public Product(String name, int image, int price, int sale) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.sale = sale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }
}

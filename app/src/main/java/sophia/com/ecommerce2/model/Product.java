package sophia.com.ecommerce2.model;

/**
 * Created by archimede on 14/06/17.
 */

public class Product {

    private String imagePath;
    private String name;
    private String description;
    private String price;

    public Product(String imagePath, String name, String description, String price) {
        this.imagePath = imagePath;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

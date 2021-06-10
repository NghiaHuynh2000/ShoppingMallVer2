package hcmute.edu.vn.mssv18110326.Model;

import java.io.Serializable;

public class Product implements Serializable {
    int  id, id_cate, type, id_color, id_size;
    String name;
    String price;
    String image;
    String description;


    public Product(int id, String name, String price, String image, int id_cate, int type, String description) {
        this.id = id;
        this.id_cate = id_cate;
        this.type = type;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    public Product(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cate() {
        return id_cate;
    }

    public void setId_cate(int id_cate) {
        this.id_cate = id_cate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

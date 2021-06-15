package hcmute.edu.vn.mssv18110326.Model;

import java.io.Serializable;
import java.sql.Blob;

public class Product implements Serializable {
    int  id, id_cate;
    String name;
    String price;
    byte[] image;
    String description;
    int quantity;


    public Product(int id, String name, String price, byte[] image, int id_cate, String description) {
        this.id = id;
        this.id_cate = id_cate;
//        this.type = type;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    public Product(int id, String name, String price, byte[] image, int id_cate, String description, int quantity) {
        this.id = id;
        this.id_cate = id_cate;
//        this.type = type;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.quantity=quantity;
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

/*   public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }*/

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity(){return quantity;}

    public void setQuantity(int quantity){this.quantity=quantity;}
}

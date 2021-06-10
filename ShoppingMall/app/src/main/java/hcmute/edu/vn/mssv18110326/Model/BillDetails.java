package hcmute.edu.vn.mssv18110326.Model;

public class BillDetails {
    int id;
    int id_bill;
    String name_product;
    String price;
    int quantity;
    String image;

    public BillDetails(int id, int id_bill, String name_product, String price, int quantity, String image) {
        this.id = id;
        this.id_bill = id_bill;
        this.name_product = name_product;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_bill() {
        return id_bill;
    }

    public void setId_bill(int id_bill) {
        this.id_bill = id_bill;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

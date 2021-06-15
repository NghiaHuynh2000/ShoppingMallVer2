package hcmute.edu.vn.mssv18110326.Model;

public class Details {
    public int id_bill;
    public int id_product;
    public String color;
    public String size;
    public int quantity;
    public Details(int id_bill, int id_product, String color, String size, int quantity) {
        this.id_bill = id_bill;
        this.id_product = id_product;
        this.color=color;
        this.size=size;
        this.quantity = quantity;
    }

    public int getId_bill() {
        return id_bill;
    }

    public void setId_bill(int id_bill) {
        this.id_bill = id_bill;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getColor(){return color;}

    public void setColor(String color){this.color=color;}

    public String getSize(){return size;}

    public void setSize(String size){this.size=size;}

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

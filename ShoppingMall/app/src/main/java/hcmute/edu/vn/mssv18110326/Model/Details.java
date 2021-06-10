package hcmute.edu.vn.mssv18110326.Model;

public class Details {
    public int id_bill;
    public int id_product;
    public int quantity;
    public Details(int id_bill, int id_product, int quantity) {
        this.id_bill = id_bill;
        this.id_product = id_product;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

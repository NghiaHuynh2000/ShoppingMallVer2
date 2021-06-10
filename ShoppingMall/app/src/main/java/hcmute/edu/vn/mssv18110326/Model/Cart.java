package hcmute.edu.vn.mssv18110326.Model;

public class Cart {
    private   int id;
    private String name;
    private int price;
    private String image;
    private String color;
    private String size;
    private int qty;

    public Cart() {

    }

    public Cart(int id, String name, int price, String image, String color, String size, int qty) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.color=color;
        this.size= size;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor(){return color;}

    public void setColor(String color){this.color=color;}

    public String getSize(){return size;}

    public void setSize(String size){ this.size=size; }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}

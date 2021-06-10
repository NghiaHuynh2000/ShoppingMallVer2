package hcmute.edu.vn.mssv18110326.Model;
public class Bill {
    public Integer id;
    public String name;
    public String date;
    public String address;

    public String email;
    public  int phone;
    public  int id_user;
    public  int total;
    public  int stt;

    public Bill(Integer id, String name, String date, String address, String email, int phone, int id_user, int total, int stt) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.id_user = id_user;
        this.total = total;
        this.stt = stt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
}

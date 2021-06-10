package hcmute.edu.vn.mssv18110326.Model;

public class Users {
    private  int mId;
    private String mName;
    private String mEmail;
    private String mPassword;
    private String mAddress;

    public Users(String mName, String mEmail, String mPassword) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
    }

    public Users(int mId, String mName, String mEmail, String mPassword) {
        this.mId = mId;
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
    }

    public Users(String mName, String mEmail, String mPassword,String mAddress) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
        this.mAddress = mAddress;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmAddress(){return mAddress;}

    public void setmAddress(String mAddress){this.mAddress=mAddress;}
}

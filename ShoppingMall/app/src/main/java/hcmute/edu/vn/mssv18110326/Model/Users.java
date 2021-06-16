package hcmute.edu.vn.mssv18110326.Model;

public class Users {
    private  int mId;
    private String mName;
    private String mEmail;
    private String mPhone;
    private String mPassword;
    private String mAddress;
    private byte[] mImage;
    private int mPermission;
    private int mStatus;

    public Users(String mName,String mEmail,String mPassword){
        this.mName = mName;
        this.mEmail=mEmail;
        this.mPassword=mPassword;
    }

    public Users(String mName, String mPhone, String mEmail, String mPassword) {
        this.mName = mName;
        this.mPhone=mPhone;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
    }

    public Users(int mId, String mName, String mEmail, String mPassword) {
        this.mId = mId;
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
    }

    public Users(String mName, String mEmail, String mPassword, String mAddress, byte[] mImage, int mPermission,int mStatus) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
        this.mAddress = mAddress;
        this.mImage = mImage;
        this.mPermission = mPermission;
        this.mStatus=mStatus;
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

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
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

    public byte[] getmImage(){return mImage;}

    public void setmImage(byte[] mImage){this.mImage=mImage;}

    public int getmPermission(){return mPermission;}

    public void setmPermission(int mPermission){this.mPermission=mPermission;}

    public int getmStatus(){return mStatus;}

    public void setmStatus(int mStatus){this.mStatus=mStatus;}
}

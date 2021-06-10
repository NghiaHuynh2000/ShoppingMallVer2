package hcmute.edu.vn.mssv18110326.Model;

public class Category {
    private  int mId;
    private String mName;
    private String mImage;

    public Category(int mId, String mName, String mImage) {
        this.mId = mId;
        this.mName = mName;
        this.mImage = mImage;
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

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}

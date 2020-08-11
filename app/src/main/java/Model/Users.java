package Model;

public class Users
{
    private String mDisplayName, mEmail, mPassword, mPhone;

    public Users()
    {

    }

    public Users(String mDisplayName, String mEmail, String mPassword, String mPhone) {
        this.mDisplayName = mDisplayName;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
        this.mPhone = mPhone;
    }

    public String getmDisplayName() {
        return mDisplayName;
    }

    public void setmDisplayName(String mDisplayName) {
        this.mDisplayName = mDisplayName;
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

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }
}

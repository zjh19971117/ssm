package entity;

public class Users {
    private int uid;
    private String uname;
    private String upass;
    private  String uemail;
    private String ugender;

    public Users(int uid, String uname, String uemail, String ugender) {
        this.uid = uid;
        this.uname = uname;
        this.uemail = uemail;
        this.ugender = ugender;
    }

    public Users(String uname, String upass, String uemail, String ugender) {
        this.uname = uname;
        this.upass = upass;
        this.uemail = uemail;
        this.ugender = ugender;
    }

    public Users(int uid, String uname, String upass, String uemail, String ugender) {
        this.uid = uid;
        this.uname = uname;
        this.upass = upass;
        this.uemail = uemail;
        this.ugender = ugender;
    }

    @Override
    public String toString() {
        return "Users{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upass='" + upass + '\'' +
                ", uemail='" + uemail + '\'' +
                ", ugender='" + ugender + '\'' +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUgender() {
        return ugender;
    }

    public void setUgender(String ugender) {
        this.ugender = ugender;
    }

    public Users() {
    }
}

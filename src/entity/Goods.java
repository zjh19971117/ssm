package entity;

public class Goods {
    private Integer gid;
    private String gname;
    private int price;
    private String photo;
    private String pic1;
    private String pic2;
    private String pic3;
    private String pic4;
    private String info;
    private Integer c1;
    private Integer c2;

    @Override
    public String toString() {
        return "Goods{" +
                "c1=" + c1 +
                ", c2=" + c2 +
                '}';
    }

    public Integer getC1() {
        return c1;
    }

    public void setC1(Integer c1) {
        this.c1 = c1;
    }

    public Integer getC2() {
        return c2;
    }

    public void setC2(Integer c2) {
        this.c2 = c2;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }

    public String getPic4() {
        return pic4;
    }

    public void setPic4(String pic4) {
        this.pic4 = pic4;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Goods(Integer gid, String gname, int price, String photo, String pic1, String pic2, String pic3, String pic4, String info) {
        this.gid = gid;
        this.gname = gname;
        this.price = price;
        this.photo = photo;
        this.pic1 = pic1;
        this.pic2 = pic2;
        this.pic3 = pic3;
        this.pic4 = pic4;
        this.info = info;
    }

    public Goods() {
    }
}

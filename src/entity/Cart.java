package entity;

public class Cart {
    private Integer cid;
    private Integer gid;
    private Integer uid;
    private int num;
    private int price;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Cart(Integer cid, Integer gid, Integer uid, int num, int price) {
        this.cid = cid;
        this.gid = gid;
        this.uid = uid;
        this.num = num;
        this.price = price;
    }

    public Cart() {
    }
}

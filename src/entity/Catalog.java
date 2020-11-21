package entity;

public class Catalog {
    private Integer cid;
    private String cname;
    private Integer cparent;
    private String url;
    private String logo;

    public Catalog(Integer cid, String cname, Integer cparent, String url, String logo) {
        this.cid = cid;
        this.cname = cname;
        this.cparent = cparent;
        this.url = url;
        this.logo = logo;
    }

    public Catalog() {
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getCparent() {
        return cparent;
    }

    public void setCparent(Integer cparent) {
        this.cparent = cparent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    @Override
    public String toString() {
        return "Catalog{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", cparent=" + cparent +
                ", url='" + url + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}

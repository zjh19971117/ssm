package entity;

public class PageBean {
    private int page;
    private int rowsPerPage;
    private int rowsNum;
    private int pageNum;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
        this.pageNum = rowsNum/rowsPerPage;
        if (rowsNum % rowsPerPage !=0){
            this.pageNum++;
        }
    }

    public int getRowsNum() {
        return rowsNum;
    }

    public void setRowsNum(int rowsNum) {
        this.rowsNum = rowsNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    @Override
    public String toString() {
        return "PageBean{" +
                "page=" + page +
                ", rowsPerPage=" + rowsPerPage +
                ", rowsNum=" + rowsNum +
                ", pageNum=" + pageNum +
                '}';
    }

}
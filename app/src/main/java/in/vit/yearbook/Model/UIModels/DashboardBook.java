package in.vit.yearbook.Model.UIModels;

public class DashboardBook {

    private Integer bookYear ;
    private String bookTitle ;


    public DashboardBook(Integer bookYear, String bookTitle) {
        this.bookYear = bookYear;
        this.bookTitle = bookTitle;
    }

    public DashboardBook() {
    }

    public Integer getBookYear() {
        return bookYear;
    }

    public void setBookYear(Integer bookYear) {
        this.bookYear = bookYear;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}

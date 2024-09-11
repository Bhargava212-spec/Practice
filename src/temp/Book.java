package temp;

public class Book {

    private String bookId;
    private Author author;

    public Book(String bookId, Author author) {
        this.bookId = bookId;
        this.author = author;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}

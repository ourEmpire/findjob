package database;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    private String id;
    private String name;
    private String author;
    private String bookUrl; //书详细的介绍的url
    private String info; //书的简介
    private String coverUrl;//书封面的url

    public Book() {
    }

    public Book(String id, String name, String author,String bookUrl, String info, String coverUrl) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.bookUrl = bookUrl;
        this.info = info;
        this.coverUrl = coverUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", bookUrl='" + bookUrl + '\'' +
                ", info='" + info + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                '}';
    }
}

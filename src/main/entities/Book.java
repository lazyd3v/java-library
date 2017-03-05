package main.entities;

public class Book implements Entity {
    private Integer id;
    private String name;
    private String publisher;
    private String publishYear;
    private Integer currentReaderId;

    public Book () {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public Integer getCurrentReaderId() {
        return currentReaderId;
    }

    public void setCurrentReaderId(Integer currentReaderId) {
        this.currentReaderId = currentReaderId;
    }
}

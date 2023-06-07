package comm.Post;

import comm.core.BaseEntity;

import jakarta.persistence.Entity;

@Entity
public class Post extends BaseEntity {

    private String title;
    private String content;

    private String author;
    //create date instance variable

    protected Post(){
        super();
    }

    public Post(String title, String content, String author){
        this();
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

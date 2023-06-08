package comm.Post;

import comm.User.User;
import comm.core.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Post extends BaseEntity {

    private String title;
    private String content;

    @ManyToOne
    private User author;

    //create date instance variable

    protected Post(){
        super();
    }

    public Post(String title, String content){
        this();
        this.title = title;
        this.content = content;
    }

    public User getUser() {
        return author;
    }

    public void setUser(User user) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

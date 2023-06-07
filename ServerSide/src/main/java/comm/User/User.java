package comm.User;

import comm.User.consts.UserConsts.Role;
import comm.core.BaseEntity;
import comm.Post.Post;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    private Role accountStatus;

    protected User(){
        super();
        posts = new ArrayList<>();
    }

    public User(String username, String password, String email){
        this();
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountStatus = accountStatus;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        post.setUser(this);
        posts.add(post);
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Role getAccountStatus(){
        return accountStatus;
    }

    public void setAccountStatus(Role role){
        this.accountStatus = role;
    }
}

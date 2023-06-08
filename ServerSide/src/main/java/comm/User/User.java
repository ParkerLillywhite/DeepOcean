package comm.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import comm.User.consts.UserConsts.Role;
import comm.core.BaseEntity;
import comm.Post.Post;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends BaseEntity {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    @NotNull
    @Size(min = 2, max = 140)
    private String username;

    @NotNull
    @Size(min = 5, max = 140)
    @JsonIgnore
    private String password;

    @Nullable
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    @JsonIgnore
    private String[] roles;

    protected User(){
        super();
        posts = new ArrayList<>();
    }

    public User(String username, String password, String email){
        this();
        this.username = username;
        setPassword(password);
        this.email = email;
        this.roles = roles;
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
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String[] getRoles(){
        return roles;
    }

    public void setRoles(String[] roles){
        this.roles = roles;
    }
}

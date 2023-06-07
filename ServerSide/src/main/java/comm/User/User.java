package comm.User;

import comm.User.consts.UserConsts.Role;
import comm.core.BaseEntity;

import jakarta.persistence.Entity;

@Entity
public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;

    private Role accountStatus;

    protected User(){
        super();
    }

    public User(String username, String password, String email){
        this();
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountStatus = accountStatus;
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

    public void setEmail(){
        this.email = email;
    }

    public Role getAccountStatus(){
        return accountStatus;
    }

    public void setAccountStatus(Role role){
        this.accountStatus = role;
    }
}

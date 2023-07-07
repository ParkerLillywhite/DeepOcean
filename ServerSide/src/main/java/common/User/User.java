package common.User;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user", schema = "Artist_DB")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
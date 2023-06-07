package comm.core;

import comm.Post.Post;
import comm.User.User;
import comm.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final UserRepository _userRepository;

    @Autowired
    public DatabaseLoader(UserRepository userRepository) {
        this._userRepository = userRepository;

    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user =  new User(
                "Borbley",
                "password123",
                "BorbleyBonson@communists.org");
        user.addPost(new Post("where are my eyeballs?", "I know you took them"));
        _userRepository.save(user);

        String[] firstNames = {
                "Dingle",
                "Sassaball",
                "Ole Jimmy",
                "Nose Face",
                "Rupert",
                "Dupendorf"
        };

        String[] lastNames = {
                "%s MackleMicheal",
                "%s DooBerry",
                "%s BisonSlapper",
                "%s NOG",
                "%s help me please"
        };

        List<User> listOfUsers = new ArrayList<>();

        IntStream.range(0, 100)
                .forEach(i -> {
                    String firstName = firstNames[i % firstNames.length];
                    String lastName = lastNames[i % lastNames.length];
                    String userName = String.format(lastName, firstName);
                    User newUser = new User(userName, "bop", "NotACommunist@Communists.org");
                    newUser.addPost(new Post("how to eat a bear", "cuisine for communists"));
                    listOfUsers.add(newUser);
                });
        _userRepository.saveAll(listOfUsers);
    }
}

package common.auth;

import common.User.UserRepository;
import common.config.JwtService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthenticationServiceTest {

    @Mock
    private UserRepository _userRepository;

    @Mock
    private PasswordEncoder _passwordEncoder;

    @Mock
    private JwtService _jwtService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister() {
        RegisterRequest request = new RegisterRequest("Borbley", "Bonson", "something@gmail.com", "password");
        
    }
}



























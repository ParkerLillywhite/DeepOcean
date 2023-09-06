package common.auth;

import common.User.Role;
import common.User.UserRepository;
import common.config.JwtService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import common.User.User;


import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {

    private UserRepository _userRepository;
    private PasswordEncoder _passwordEncoder;
    private JwtService _jwtService;
    private AuthenticationManager _authenticationManager;
    private AuthenticationService _authenticationService;

    @BeforeEach
    public void setUp() {
        _userRepository = Mockito.mock(UserRepository.class);
        _passwordEncoder = Mockito.mock(PasswordEncoder.class);
        _jwtService = Mockito.mock(JwtService.class);
        _authenticationManager = Mockito.mock(AuthenticationManager.class);

        _authenticationService = new AuthenticationService(
                _userRepository,
                _passwordEncoder,
                _jwtService,
                _authenticationManager
        );
    }

    @Test
    public void testRegister() {
        RegisterRequest request = new RegisterRequest("Borbley", "Bonson", "something@gmail.com", "password");
        when(_passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");

        AuthenticationResponse response = _authenticationService.register(request);

        assertEquals("encodedPassword", response.getToken());
    }

    @Test
    public void testAuthenticate() {
        AuthenticationRequest request = new AuthenticationRequest("borbleybonson@bonsonsbagels.com", "password");
        User user = User
                .builder()
                .firstname("Borbley")
                .lastname("Bonson")
                .email("borbleybonson@bonsonsbagels.com")
                .password("encodedPassword")
                .role(Role.USER)
                .build();

        when(_userRepository.findByEmail(request.getEmail())).thenReturn(java.util.Optional.of(user));
        when(_jwtService.generateToken(user)).thenReturn("jwtToken");

        AuthenticationResponse response = _authenticationService.authenticate(request);

        assertEquals("jwtToken", response.getToken());
    }
}



























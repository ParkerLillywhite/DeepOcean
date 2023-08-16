package common.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    // Inject the JwtAuthenticationFilter and AuthenticationProvider using constructor injection
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
        // Start configuring the security filter chain using the provided HttpSecurity object
        http
                .csrf()
                .disable()  // Disable Cross-Site Request Forgery (CSRF) protection

                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**")
                .permitAll()  // Allow unauthenticated access to URLs under /api/v1/auth

                .anyRequest()
                .authenticated()  // Require authentication for any other request

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Set session creation policy to stateless

                .and()
                .authenticationProvider(authenticationProvider)  // Set the specified AuthenticationProvider

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);  // Add JwtAuthenticationFilter before UsernamePasswordAuthenticationFilter

        // Build and return the configured SecurityFilterChain
        return http.build();
    }
}

package RomanoPietro.u5w3Progetto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.formLogin(http -> http.disable());

        httpSecurity.csrf(http -> http.disable());

        httpSecurity.sessionManagement(http -> http.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Autorizzo le richieste agli endpoint relativi agli eventi solo per utenti con ruoli USER o EVENT_ORGANIZER
        httpSecurity.authorizeHttpRequests(auth -> auth.requestMatchers("/events/**")
                .hasAnyRole("USER", "EVENT_ORGANIZER").anyRequest().permitAll());

        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder getBcript() {
        return new BCryptPasswordEncoder(12);
    }

}

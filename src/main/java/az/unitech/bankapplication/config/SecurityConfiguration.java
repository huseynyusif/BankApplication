package az.unitech.bankapplication.config;

import az.unitech.bankapplication.entity.UserEntity;
import az.unitech.bankapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/accounts/create","/all-accounts").hasAnyRole("ADMIN")
                                .requestMatchers("/my-account").hasAnyRole(("USER"))
                                .requestMatchers("/**")
                                .permitAll()

                ).formLogin(f -> f.defaultSuccessUrl("/currency-rates/latest"))
                .logout(logout ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessHandler((request, response, authentication)
                                        -> SecurityContextHolder.clearContext())
                                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123"))
                .roles("ADMIN")
                .build();

        List<UserEntity> users = userRepository.findAll();
        List<UserDetails> userDetailsList = new ArrayList<>();
        for (UserEntity userEntity : users){
            UserDetails userDetails = User.builder()
                    .username(userEntity.getUserName())
                    .password(passwordEncoder.encode(userEntity.getPassword()))
                    .roles("USER")
                    .build();
            userDetailsList.add(userDetails);
        }

        userDetailsList.add(admin);

        return new InMemoryUserDetailsManager(userDetailsList);
    }
}

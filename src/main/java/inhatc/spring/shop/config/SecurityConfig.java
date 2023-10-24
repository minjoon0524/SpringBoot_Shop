package inhatc.spring.shop.config;

<<<<<<< HEAD

=======
>>>>>>> origin/master
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
<<<<<<< HEAD

        http.formLogin((form) -> form
                .loginPage("/member/login")
                .defaultSuccessUrl("/")
                .failureUrl("/member/login/error")
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll() );

        http.authorizeHttpRequests(request->request
                .requestMatchers("css/**").permitAll()
                .requestMatchers("/","/member/**").permitAll()
                .anyRequest().authenticated()
        );

        http.logout(Customizer.withDefaults());


=======
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
>>>>>>> origin/master
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

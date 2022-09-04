package ru.otus.mvcspring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.otus.mvcspring.domain.User;
import ru.otus.mvcspring.repositories.UserRepository;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    @Autowired
    public SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/book/getAll", "/book/delete", "/book/edit", "/book/create",
                        "/comment/getAllByBookId", "/comment/delete", "/comment/create", "/comment/edit")
                .authenticated()
                .and()
                .formLogin();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        User userAdmin = userRepository.findUserByName("admin");
        auth.inMemoryAuthentication()
                .withUser(userAdmin.getName()).password(userAdmin.getPassword()).roles("ADMIN");
    }

}


package ru.kata.spring.boot_security.demo.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.services.UserService;


@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //    private final SuccessUserHandler successUserHandler;
//
//    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
//        this.successUserHandler = successUserHandler;
//    }

//    private UserServiceImpl userServiceImpl;
//@Autowired
//public void setUserService(UserServiceImpl userServiceImpl) {
//    this.userServiceImpl = userServiceImpl;
//}
    private final UserService userService;

    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http
//                .authorizeRequests()
//                .antMatchers("/", "/index").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().successHandler(successUserHandler)
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
        http.authorizeRequests()
                .antMatchers("/users").authenticated()
                .antMatchers("/admins/**").hasRole("ADMIN")
                .antMatchers("/managers/**").hasAuthority("AUTH_MANAGERS")
                .antMatchers("/users/**").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutSuccessUrl("/");
    }

    //         аутентификация inMemory
//    @Bean
//    public UserDetailsService users() {
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$12$npwjlEUtsgRx4PLxJXIOI.tMD8178g0kAE2QJPuW0ulKSeA54JhBy")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$vsoeAETi5OoVk81nWhYoI.PQqD3m1zl.0wYX.A7F1JJbQT8CTcrAi")
//                .roles("ADMIN", "USER")
//                .build();
//
//        UserDetails manager = User.builder()
//                .username("manager")
//                .password("{bcrypt}$2a$12$Nuh5uUZjcLTifZf5GNND5uabPgiSalMBZ3y7miZF5/CxAaJzd07Ni")
//                .roles("MANAGER", "ADMIN", "USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin, manager);
//    }
//
//    }

    //JDBC Auth

//    @Bean
//    public JdbcUserDetailsManager users(DataSource dataSource) {
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$12$npwjlEUtsgRx4PLxJXIOI.tMD8178g0kAE2QJPuW0ulKSeA54JhBy")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$vsoeAETi5OoVk81nWhYoI.PQqD3m1zl.0wYX.A7F1JJbQT8CTcrAi")
//                .roles("ADMIN", "USER")
//                .build();
//
//        UserDetails manager = User.builder()
//                .username("manager")
//                .password("{bcrypt}$2a$12$Nuh5uUZjcLTifZf5GNND5uabPgiSalMBZ3y7miZF5/CxAaJzd07Ni")
//                .roles("MANAGER", "ADMIN", "USER").authorities("AUTH_MANAGERS")
//                .build();
//
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//        if (jdbcUserDetailsManager.userExists(user.getUsername())) {
//            jdbcUserDetailsManager.deleteUser(user.getUsername());
//        }
//        if (jdbcUserDetailsManager.userExists(admin.getUsername())) {
//            jdbcUserDetailsManager.deleteUser(admin.getUsername());
//        }
//        if (jdbcUserDetailsManager.userExists(manager.getUsername())) {
//            jdbcUserDetailsManager.deleteUser(manager.getUsername());
//        }
//
//        jdbcUserDetailsManager.createUser(user);
//        jdbcUserDetailsManager.createUser(admin);
//        jdbcUserDetailsManager.createUser(manager);
//
//        return jdbcUserDetailsManager;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

}
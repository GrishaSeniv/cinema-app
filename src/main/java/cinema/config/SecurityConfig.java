package cinema.config;

import cinema.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/register").anonymous()
                .mvcMatchers(HttpMethod.GET, "/cinema-halls", "/movies",
                        "/movie-sessions/available").hasAnyRole(
                        Role.RoleName.ADMIN.name(), Role.RoleName.USER.name())
                .mvcMatchers(HttpMethod.POST, "/cinema-halls", "/movies",
                        "/movie-sessions").hasRole(Role.RoleName.ADMIN.name())
                .mvcMatchers(HttpMethod.PUT, "PUT: /movie-sessions/**")
                .hasRole(Role.RoleName.ADMIN.name())
                .mvcMatchers(HttpMethod.DELETE, "DELETE: /movie-sessions/**")
                .hasRole(Role.RoleName.ADMIN.name())
                .mvcMatchers(HttpMethod.GET, "/orders", "/shopping-carts/by-user")
                .hasRole(Role.RoleName.USER.name())
                .mvcMatchers(HttpMethod.POST, "/orders/complete")
                .hasRole(Role.RoleName.USER.name())
                .mvcMatchers(HttpMethod.PUT, "/shopping-carts/movie-sessions")
                .hasRole(Role.RoleName.USER.name())
                .mvcMatchers(HttpMethod.GET, "/users/by-email")
                .hasRole(Role.RoleName.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/movies", true)
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}

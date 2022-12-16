package am.itspace.hotel.config;

import am.itspace.hotel.entity.enums.UserType;
import am.itspace.hotel.security.CurrentUserDetailServiceImpl;
import am.itspace.hotel.security.JWTAuthenticationTokenFilter;
import am.itspace.hotel.security.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final CurrentUserDetailServiceImpl userDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/room/add").hasAuthority(UserType.ADMIN.name())
                .antMatchers("/roomUpdate").hasAuthority(UserType.ADMIN.name())
                .antMatchers("/room/{id}").hasAuthority(UserType.ADMIN.name())
                .antMatchers("/book").hasAnyAuthority(UserType.ADMIN.name(),UserType.USER.name())
                .antMatchers("/book/delete").hasAnyAuthority(UserType.ADMIN.name(),UserType.USER.name())
                .antMatchers("/rating").hasAuthority(UserType.USER.name())
                .anyRequest().permitAll();

        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        http.headers().cacheControl();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public JWTAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JWTAuthenticationTokenFilter();
    }

}
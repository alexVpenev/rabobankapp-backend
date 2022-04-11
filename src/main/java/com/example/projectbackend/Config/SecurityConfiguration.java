package com.example.projectbackend.Config;

import com.example.projectbackend.Filter.JWTAuthenticationFilter;
import com.example.projectbackend.Filter.JWTAuthorizationFilter;
import com.example.projectbackend.Service.AuthenticationUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationUserDetailService authenticationUserDetailService;


    @Override protected void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/account/restricted").authorizeRequests();
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and().csrf().disable();
        http.cors().and().csrf().disable().authorizeRequests()
//                .anyRequest().authenticated()
                .antMatchers(HttpMethod.POST, AuthenticationConfigConstants.SIGN_UP_URL).permitAll()
                //ROLE BASED AUTHENTICATION START
//                .antMatchers("/account/**/**").permitAll()
                .antMatchers("/account/*").permitAll()
                .antMatchers("/charity").permitAll()
                .antMatchers("/charity/**").permitAll()
                .antMatchers("/charity/**/**").permitAll()
                .antMatchers("/donation").permitAll()
                .antMatchers("/donation/**").permitAll()
                .antMatchers("/subscribe").hasAnyAuthority("USER")
                .antMatchers("/subscribe/personal").hasAnyAuthority("USER")
                .antMatchers("/subscribe/delete").hasAnyAuthority("USER")
                .antMatchers("/search").hasAnyAuthority("USER")

                .antMatchers("/account/login/restricted").authenticated()
                .antMatchers("/account").permitAll() // HAS TO CHANGE ------------------
//            .antMatchers("/api/library/author/**").hasAnyAuthority("ADMIN")
//            .antMatchers("/api/library/member/**").hasAnyAuthority("ADMIN")
                //ROLE BASED AUTHENTICATION END
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //single sing-on
                .and()
                .oauth2Login();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationUserDetailService).passwordEncoder(bCryptPasswordEncoder);
    }
}
package com.prograd.EmployeeManagement.configuration;

import com.prograd.EmployeeManagement.security.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private Security security;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/employees/**").hasAnyAuthority("Admin","Manager")
                .antMatchers(HttpMethod.GET,"/api/employees/**").hasAnyAuthority("Admin","Manager")
                .antMatchers(HttpMethod.DELETE,"/api/employees/**").hasAnyAuthority("Admin","Manager")
                .antMatchers(HttpMethod.PUT,"/api/employees/**").hasAnyAuthority("Admin","Manager")
                .antMatchers(HttpMethod.POST,"/api/assets/**").hasAnyAuthority("Admin","Manager")
                .antMatchers(HttpMethod.GET,"/api/assets/**").hasAnyAuthority("Admin","Employee","Manager")
                .antMatchers(HttpMethod.PUT,"/api/assets/**").hasAnyAuthority("Admin","Manager")
                .antMatchers(HttpMethod.DELETE,"/api/assets/**").hasAnyAuthority("Admin","Manager")
                .antMatchers(HttpMethod.POST,"/api/organisations/**").hasAnyAuthority("Admin")
                .antMatchers(HttpMethod.GET,"/api/organisations/**").hasAnyAuthority("Admin","Manager")
                .antMatchers(HttpMethod.PUT,"/api/organisations/**").hasAnyAuthority("Admin")
                .antMatchers(HttpMethod.DELETE,"/api/organisations/**").hasAnyAuthority("Admin")
                .antMatchers(HttpMethod.GET,"/api/employee/**").hasAnyAuthority("Employee")
                .anyRequest().authenticated()
                .and().httpBasic();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.security).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();

    }
}
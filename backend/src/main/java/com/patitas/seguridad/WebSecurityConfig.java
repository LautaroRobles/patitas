package com.patitas.seguridad;

import com.patitas.seguridad.jwt.JwtAuthorizationFilter;
import com.patitas.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilterBean() {
        return new JwtAuthorizationFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .cors().and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/publicacion").permitAll()
            .antMatchers("/api/persona").permitAll()
            .antMatchers("/api/persona/**").permitAll()
            .antMatchers("/api/usuario").permitAll()
            .antMatchers("/api/usuario/**").permitAll()
            .antMatchers("/api/mascota").permitAll()
            .antMatchers("/api/mascota/**").permitAll()
            .antMatchers("/api/publicacion").permitAll()
            .antMatchers("/api/publicacion/**").permitAll()
            .antMatchers("/api/organizacion").permitAll()
            .antMatchers("/api/auth/**").permitAll()
            .antMatchers("/api/**").authenticated().and()
            .addFilterAfter(jwtAuthorizationFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
}

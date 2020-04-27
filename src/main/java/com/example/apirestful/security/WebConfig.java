package com.example.apirestful.security;

import com.example.apirestful.service.userDetailsServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter  {
    
    @Autowired
    private userDetailsServiceImplement userDetailsServiceImplement;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImplement)
        .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            /*Ativando a proteção contra usuario que não estão validados por token */
            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .disable().authorizeRequests()
            .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
            /*URL de Logout - REDIRECIONA APÓS USER DESLOGAR DO SISTEMA*/
            .anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
            /*mapeia url de logout e invalida o usuario*/
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        
            /* Filtra requisições de login para autenticação*/
            .and().addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)

            /* Filtra demais requisições parta verificar a presença do token JWT no HEADER HTTP*/
            .addFilterBefore(new JWTApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);
        }
}
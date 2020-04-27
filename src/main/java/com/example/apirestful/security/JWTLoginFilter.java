package com.example.apirestful.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.apirestful.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/* Estabele nosso gerenciador do Token*/
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

  /* Configurando o gerenciador de autenticação */
  protected JWTLoginFilter(final String url, final AuthenticationManager authenticationManager) {
    /* Obriga a autenticar a URL */
    super(new AntPathRequestMatcher(url));

    /* Gerenciador de de autenticação */
    setAuthenticationManager(authenticationManager);
  }

  /* Retorna um usuario ao procesar a autenticação */
  @Override
  public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response)
                                              throws AuthenticationException, IOException, ServletException {

    /* Está pegando o token para validar */
    Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

    /* Retorna o usuario login, senha e credentiais */
    return getAuthenticationManager().authenticate(
        new UsernamePasswordAuthenticationToken(
          usuario.getEmail(),
          usuario.getSenha(),
          usuario.getAuthorities()));
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                          Authentication authentication) 
                                          throws IOException, ServletException {

    new JWTokenAutenticacaoService().gerarToken(response, authentication.getName());
  }
}
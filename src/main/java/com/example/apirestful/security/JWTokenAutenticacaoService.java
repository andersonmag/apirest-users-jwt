package com.example.apirestful.security;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.apirestful.ApplicationContextLoad;
import com.example.apirestful.model.Usuario;
import com.example.apirestful.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTokenAutenticacaoService {

    /* Padrão de Prefixo/"Variavel" JSON de envio e localização do Token */
    private static final String HEADER_STRING = "Authorization";

    /* Prefixo padrão para composição do Token. Seguido do HEADER_STRING */
    private static final String TOKEN_PREFIXO = "Bearer";

    /*
     * Senha secreta unica para compor a autenticação, será juntada ao login +
     * Header + outras informações e assim é gerado o valor do TOKEN JWT
     */
    private static final String TOKEN_SECRETO_VALOR = "SENHASECRETA";

    /* Tempo de validade do Token */
    private static final long TEMPO_VALIDADE = 172800000;

    /*
     * Gerando token de autenticação e adicionando ao cabeçalho e resposta
     * HTTP/Navegador
     */
    public void gerarToken(HttpServletResponse response, String email) throws IOException {

        /* Montagem do valor do Token(ex: iauidahwkdhahiuh7333q7ty7tetga) */
        String JWT_VALOR = Jwts.builder() /* Chama o gerador de Token */
                .setSubject(email) /* Adiciona o Usuario(Email/Username) */
                .setExpiration(new Date(System.currentTimeMillis() + TEMPO_VALIDADE)) /* Tempo de Validade/Expiração */
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRETO_VALOR)
                .compact(); /* Algoritmo e senha secreta para compactação e configuração do Token */

        /* Junta o Token com o Prefixo */
        String TOKEN_FINAL = TOKEN_PREFIXO + " " + JWT_VALOR; /* Bearer iauidahwkdhahiuh7333q7ty7tetga */

        /* Adiciona no cabeçalho HTTP/Navegador | Envio */
        response.addHeader(HEADER_STRING, TOKEN_FINAL); /* Authorization : Bearer iauidahwkdhahiuh7333q7ty7tetga */

        /* Escreve TOKEN como resposta para o corpo HTTP */
        response.getWriter().write("{\"Authorization\": \"" + TOKEN_FINAL
                + "\"}"); /* "Authorization" : "Bearer iauidahwkdhahiuh7333q7ty7tetga" */

    }

    /* Validando usuario, das próximas vezes, após ter gerado token */
    /* Retorna o usuario validado com token ou caso não seja valido, retorna null */
    public Authentication validarTokenAuthentication(HttpServletRequest request) {

        /* Pega o token enviado no cabeçalho HTTP/Navegador */
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {

            /* Faz a validação do token do usuario na requisição. */
            String email = Jwts.parser() /*
                                          * Dessa vez vai descompactar o Token por completo, até sobrar apenas o
                                          * Usuario(Email/username)
                                          */
                    .setSigningKey(TOKEN_SECRETO_VALOR) /*
                                                         * Senha secreta utilizada para gerar o token. result: Bearer
                                                         * iauidahwkdhahiuh7333q7ty7tetga
                                                         */
                    .parseClaimsJws(token.replace(TOKEN_PREFIXO, "")) /*
                                                                       * Retirando o prefixo. result:
                                                                       * iauidahwkdhahiuh7333q7ty7tetga
                                                                       */
                    .getBody().getSubject(); /* Pegando somente o username/email do usuario */

            if (email != null) {

                Usuario usuario = ApplicationContextLoad.getApplicationContext().getBean(UsuarioRepository.class)
                        .findByEmail(email);

                if (usuario != null) {
                    /* Retorna usuario logado */
                    return new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha(),
                            usuario.getAuthorities());
                }
            }
        }

        return null;
    }
}
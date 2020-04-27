package com.example.apirestful.service;

import com.example.apirestful.model.Usuario;
import com.example.apirestful.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userDetailsServiceImplement implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(username);

        if(usuario == null)
            throw new UsernameNotFoundException("Usuario não encontrado");
        else
            return new User(usuario.getEmail(),
                            usuario.getPassword(), 
                            usuario.getAuthorities());
    }
}
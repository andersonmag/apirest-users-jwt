package com.example.apirestful.service;

import java.util.List;
import javax.validation.Valid;
import com.example.apirestful.model.Usuario;
import com.example.apirestful.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UsuarioServiceImplement implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario save(@RequestBody @Valid Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}
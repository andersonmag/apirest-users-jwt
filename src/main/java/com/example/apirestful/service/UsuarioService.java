package com.example.apirestful.service;

import java.util.List;
import com.example.apirestful.model.Usuario;

public interface UsuarioService {

    public Usuario save(Usuario usuario);
    public List<Usuario> findAll();
    public Usuario findById(Long id);
    public void delete(Long id);
    
}
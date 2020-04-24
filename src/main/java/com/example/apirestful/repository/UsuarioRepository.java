package com.example.apirestful.repository;

import javax.transaction.Transactional;
import com.example.apirestful.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
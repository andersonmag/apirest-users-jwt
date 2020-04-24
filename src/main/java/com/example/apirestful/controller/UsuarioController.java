package com.example.apirestful.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import javax.validation.Valid;
import com.example.apirestful.model.Usuario;
import com.example.apirestful.service.UsuarioService;

@Api(value = "APIRest Usuarios")
@RequestMapping("/usuario")
@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @ApiOperation(value = "Lista de Usuarios/JSON")
    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Cadastrar Novo Usuario/Envio em formato JSON")
    @PostMapping
    public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario usuario) {

        usuario.getNotas().forEach(t -> t.setUsuario(usuario));
        return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualizar Usuario Existente, Id via URL/Envio das alterações em JSON")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Usuario> update(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
    usuario.setId(id);
    usuario.getNotas().forEach(t -> t.setUsuario(usuario));
       return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.OK);
    }


    @ApiOperation(value = "Buscar Usuario Por Id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> searchById(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Excluir Usuario por Id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
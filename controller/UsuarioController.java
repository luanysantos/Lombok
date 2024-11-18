package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entities.Usuario;
import com.example.demo.services.UsuarioService;

import jakarta.validation.Valid;

public class UsuarioController {

	private final UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscaUsuarioControlId(@PathVariable Long id) {
		Usuario usuario = usuarioService.buscaUsuarioId(id);
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Usuario>> buscaTodosUsuariosControl() {
		List<Usuario> Usuario = usuarioService.buscaTodosUsuarios();
		return ResponseEntity.ok(Usuario);
	}

	@PostMapping("/")
	public ResponseEntity<Usuario> salvaUsuariosControl(@RequestBody @Valid Usuario Usuario) {
		Usuario salvaUsuario = usuarioService.salvaUsuario(Usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaUsuario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> alteraUsuarioControl(@PathVariable Long id, @RequestBody @Valid Usuario Usuario) {
		Usuario alteraUsuario = usuarioService.alterarUsuario(id, Usuario);
		if (alteraUsuario != null) {
			return ResponseEntity.ok(Usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> apagaUsuarioControl(@PathVariable Long id) {
		boolean apagar = usuarioService.apagarUsuario(id);
		if (apagar) {
			return ResponseEntity.ok().body("O usuario foi excluido com sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}

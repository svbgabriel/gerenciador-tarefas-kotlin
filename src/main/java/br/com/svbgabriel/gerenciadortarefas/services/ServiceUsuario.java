package br.com.svbgabriel.gerenciadortarefas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.svbgabriel.gerenciadortarefas.models.Usuario;
import br.com.svbgabriel.gerenciadortarefas.repositories.RepositoryUsuario;

@Service
public class ServiceUsuario {

	@Autowired
	private RepositoryUsuario repositoryUsuario;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Usuario encontrarPorEmail(String email) {
		return repositoryUsuario.findByEmail(email);
	}

	public void salvar(Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		repositoryUsuario.save(usuario);
	}
}

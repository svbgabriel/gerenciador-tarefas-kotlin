package br.com.svbgabriel.gerenciadortarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.svbgabriel.gerenciadortarefas.models.Usuario;

public interface RepositoryUsuario extends JpaRepository<Usuario, Long> {
	Usuario findByEmail(String email);
}

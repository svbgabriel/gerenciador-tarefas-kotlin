package br.com.svbgabriel.gerenciadortarefas.repositories

import org.springframework.data.jpa.repository.JpaRepository
import br.com.svbgabriel.gerenciadortarefas.models.Usuario

interface RepositoryUsuario : JpaRepository<Usuario, Long> {
	fun findByEmail(email: String): Usuario?
}
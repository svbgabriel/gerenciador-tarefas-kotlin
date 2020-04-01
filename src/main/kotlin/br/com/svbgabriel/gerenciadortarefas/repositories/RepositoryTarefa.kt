package br.com.svbgabriel.gerenciadortarefas.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import br.com.svbgabriel.gerenciadortarefas.models.Tarefa

interface RepositoryTarefa : JpaRepository<Tarefa, Long> {
	@Query("SELECT t FROM Tarefa t WHERE t.usuario.email = :emailUsuario")
	fun carregarTarefasPorUsuario(@Param("emailUsuario") email: String): List<Tarefa>?
}
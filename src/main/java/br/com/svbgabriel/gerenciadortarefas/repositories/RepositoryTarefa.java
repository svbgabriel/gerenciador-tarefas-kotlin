package br.com.svbgabriel.gerenciadortarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.svbgabriel.gerenciadortarefas.models.Tarefa;

public interface RepositoryTarefa extends JpaRepository<Tarefa, Long>{

}

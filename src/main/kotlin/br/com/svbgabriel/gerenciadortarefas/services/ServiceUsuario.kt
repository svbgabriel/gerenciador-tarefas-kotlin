package br.com.svbgabriel.gerenciadortarefas.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import br.com.svbgabriel.gerenciadortarefas.models.Usuario
import br.com.svbgabriel.gerenciadortarefas.repositories.RepositoryUsuario

@Service
class ServiceUsuario(private val repositoryUsuario: RepositoryUsuario, private val passwordEncoder: BCryptPasswordEncoder) {
	
	fun encontrarPorEmail(email: String): Usuario? {
		return repositoryUsuario.findByEmail(email)
	}

	fun salvar(usuario: Usuario) {
		usuario.senha = passwordEncoder.encode(usuario.senha)
		repositoryUsuario.save(usuario)
	}
}

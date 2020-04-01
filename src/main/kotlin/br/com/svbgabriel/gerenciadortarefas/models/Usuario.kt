package br.com.svbgabriel.gerenciadortarefas.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length

@Entity
@Table(name = "usr_usuarios")
data class Usuario ( 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_id")
	val id: Long = 0,

	@Column(name = "usr_email", nullable = false, length = 100)
	@Length(min = 5, max = 100, message = "O e-mail deve conter entre 5 e 100 caracteres")
	var email: String = "",

	@Column(name = "usr_senha", nullable = false, length = 100)
	var senha: String = "",

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	var tarefas: List<Tarefa> = emptyList()
)
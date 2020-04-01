package br.com.svbgabriel.gerenciadortarefas.models

import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length
import org.springframework.format.annotation.DateTimeFormat

@Entity
@Table(name = "tar_tarefas")
data class Tarefa (
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tar_id")
	val id: Long = 0,

	@Column(name = "tar_titulo", length = 50, nullable = false)
	@Length(max = 50, min = 3, message = "O título deve conter entre 3 e 50 caracteres")
	val titulo: String = "",

	@Column(name = "tar_descricao", length = 100, nullable = true)
	@Length(max = 100, message = "A descrição deve conter até 100 caracteres")
	var descricao: String = "",

	@Column(name = "tar_data_expiracao", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	val dataExpiracao: Date = Date(),

	@Column(name = "tar_concluida", nullable = false)
	var concluida: Boolean = false,

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usr_id")
	var usuario: Usuario? = Usuario()
)
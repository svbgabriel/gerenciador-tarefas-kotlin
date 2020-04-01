package br.com.svbgabriel.gerenciadortarefas.controllers

import java.util.Date
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import br.com.svbgabriel.gerenciadortarefas.models.Tarefa
import br.com.svbgabriel.gerenciadortarefas.models.Usuario
import br.com.svbgabriel.gerenciadortarefas.repositories.RepositoryTarefa
import br.com.svbgabriel.gerenciadortarefas.services.ServiceUsuario

@Controller
@RequestMapping("/tarefas")
class TarefasController(private val repositoryTarefa: RepositoryTarefa, private val serviceUsuario: ServiceUsuario) {

	@GetMapping("/listar")
	fun listar(request: HttpServletRequest): ModelAndView {
		val mv = ModelAndView()
		mv.setViewName("tarefas/listar")
		val emailUsuario = request.getUserPrincipal().getName()
		mv.addObject("tarefas", repositoryTarefa.carregarTarefasPorUsuario(emailUsuario))
		return mv
	}

	@GetMapping("/inserir")
	fun inserir(): ModelAndView {
		val mv = ModelAndView()
		mv.setViewName("tarefas/inserir")
		mv.addObject("tarefa", Tarefa())
		return mv
	}

	@PostMapping("/inserir")
	fun inserir(@Valid tarefa: Tarefa, result: BindingResult, request: HttpServletRequest): ModelAndView {
		val mv = ModelAndView()
		if (tarefa.dataExpiracao.before(Date())) {
			result.rejectValue(
				"dataExpiracao", "tarefa.dataExpiracaoInvalida",
				"A data de expiração não pode ser anterior a data atual."
			)
		}
		if (result.hasErrors()) {
			mv.setViewName("tarefas/inserir")
			mv.addObject(tarefa)
		} else {
			val emailUsuario = request.getUserPrincipal().getName()
			val usuarioLogado = serviceUsuario.encontrarPorEmail(emailUsuario)
			tarefa.usuario = usuarioLogado
			repositoryTarefa.save(tarefa)
			mv.setViewName("redirect:/tarefas/listar")
		}
		return mv
	}

	@GetMapping("/alterar/{id}")
	fun alterar(@PathVariable("id") id: Long): ModelAndView {
		val mv = ModelAndView()
		val tarefa = repositoryTarefa.getOne(id)
		mv.addObject("tarefa", tarefa)
		mv.setViewName("tarefas/alterar")
		return mv
	}

	@PostMapping("/alterar")
	fun alterar(@Valid tarefa: Tarefa, result: BindingResult): ModelAndView {
		val mv = ModelAndView()
		if (tarefa.dataExpiracao.before(Date())) {
			result.rejectValue(
				"dataExpiracao", "tarefa.dataExpiracaoInvalida",
				"A data de expiração não pode ser anterior a data atual."
			)
		}
		if (result.hasErrors()) {
			mv.setViewName("tarefas/alterar")
			mv.addObject(tarefa)
		} else {
			mv.setViewName("redirect:/tarefas/listar")
			repositoryTarefa.save(tarefa)
		}
		return mv
	}

	@GetMapping("/excluir/{id}")
	fun excluir(@PathVariable("id") id: Long): String {
		repositoryTarefa.deleteById(id)
		return "redirect:/tarefas/listar"
	}

	@GetMapping("/concluir/{id}")
	fun concluir(@PathVariable("id") id: Long): String {
		val tarefa: Tarefa = repositoryTarefa.getOne(id)
		tarefa.concluida = true
		repositoryTarefa.save(tarefa)
		return "redirect:/tarefas/listar"
	}
}

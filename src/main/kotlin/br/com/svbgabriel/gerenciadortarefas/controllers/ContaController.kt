package br.com.svbgabriel.gerenciadortarefas.controllers

import javax.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import br.com.svbgabriel.gerenciadortarefas.models.Usuario
import br.com.svbgabriel.gerenciadortarefas.services.ServiceUsuario

@Controller
class ContaController(private val serviceUsuario: ServiceUsuario) {

	@GetMapping("/login")
	fun login(): String {
		return "conta/login"
	}

	@GetMapping("/registration")
	fun registrar(): ModelAndView {
		val mv = ModelAndView()
		mv.setViewName("conta/registrar")
		mv.addObject("usuario", Usuario())
		return mv
	}

	@PostMapping("/registration")
	fun registrar(@Valid usuario: Usuario, result: BindingResult): ModelAndView {
		val mv = ModelAndView()
		val usr = serviceUsuario.encontrarPorEmail(usuario.email)
		if (usr != null) {
			result.rejectValue("email", "", "Usuário já cadastrado")
		}
		if (result.hasErrors()) {
			mv.setViewName("conta/registrar")
			mv.addObject("usuario", usuario)
		} else {
			serviceUsuario.salvar(usuario)
			mv.setViewName("redirect:/login")
		}
		return mv
	}
}

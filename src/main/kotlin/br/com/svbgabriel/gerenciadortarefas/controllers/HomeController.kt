package br.com.svbgabriel.gerenciadortarefas.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class HomeController {
	@GetMapping("/")
	fun home(): ModelAndView? {
		val mv = ModelAndView()
		mv.setViewName("home/home")
		return mv
	}
}
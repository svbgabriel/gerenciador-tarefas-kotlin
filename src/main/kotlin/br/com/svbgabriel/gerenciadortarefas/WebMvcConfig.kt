package br.com.svbgabriel.gerenciadortarefas

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {
	@Bean
	fun passwordEncoder(): BCryptPasswordEncoder? {
		val passwordEncoder = BCryptPasswordEncoder()
		return passwordEncoder
	}
}
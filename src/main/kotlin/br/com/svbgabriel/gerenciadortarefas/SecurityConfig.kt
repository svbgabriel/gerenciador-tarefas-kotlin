package br.com.svbgabriel.gerenciadortarefas

import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
class SecurityConfig(
	private val passwordEncoder: BCryptPasswordEncoder,
	private val dataSource: DataSource,
	@Value("\${spring.queries.users-query}")
	private val userQuery: String,
	@Value("\${spring.queries.roles-query}")
	private val roleQuery: String
) : WebSecurityConfigurerAdapter() {

	@Throws(Exception::class)
	override protected fun configure(auth: AuthenticationManagerBuilder?) {
		auth!!.jdbcAuthentication().usersByUsernameQuery(userQuery).authoritiesByUsernameQuery(roleQuery)
			.dataSource(dataSource).passwordEncoder(passwordEncoder)
	}

	@Throws(Exception::class)
	override protected fun configure(http: HttpSecurity?) {
		http!!
			.authorizeRequests()
			.antMatchers("/login").permitAll().antMatchers("/registration").permitAll()
			.anyRequest()
			.authenticated().and().csrf().disable()
			.formLogin()
			.loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/")
			.usernameParameter("email").passwordParameter("senha")
			.and().logout()
			.logoutRequestMatcher(AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
	}

	@Throws(Exception::class)
	override fun configure(web: WebSecurity?) {
		web!!.ignoring().antMatchers("/webjars/**")
	}
}
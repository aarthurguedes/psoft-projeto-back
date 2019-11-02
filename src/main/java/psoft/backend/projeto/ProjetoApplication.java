package psoft.backend.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import psoft.backend.projeto.filtros.TokenFilter;

@SpringBootApplication
public class ProjetoApplication {

	@Bean
	public FilterRegistrationBean<TokenFilter> filterJwt() {
		FilterRegistrationBean<TokenFilter> filterRB = new FilterRegistrationBean<>();
		filterRB.setFilter(new TokenFilter());
		filterRB.addUrlPatterns("/v1/usuarios");
		return filterRB;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);
	}

}

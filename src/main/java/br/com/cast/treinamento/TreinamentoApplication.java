package br.com.cast.treinamento;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.cast.treinamento.converter.UsuarioConverter;
import br.com.cast.treinamento.model.Usuario;
import br.com.cast.treinamento.security.SwaggerSecurityConfig;
import br.com.cast.treinamento.service.UsuarioService;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TreinamentoApplication {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioConverter usuarioConverter;

	@Autowired
	private SwaggerSecurityConfig swaggerSecurityConfig;

	public static void main(String[] args) {
		SpringApplication.run(TreinamentoApplication.class, args);
	}

	/***
	 * Carga de um usuário para teste
	 */
	@PostConstruct
	private void init() {

		// Cria um usuário no primeiro uso da aplicação para teste.
		if (usuarioService.consultarTodos().isEmpty()) {
			Usuario usuario = new Usuario(null, "andre.bacaglini@castgroup.com.br", "André Bacalgini",
					new BCryptPasswordEncoder().encode("123456"));
			usuarioService.incluir(usuarioConverter.toEntityToDto(usuario));
		}
	}

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2)
				.securityContexts(Arrays.asList(swaggerSecurityConfig.securityContext()))
				.securitySchemes(Arrays.asList(swaggerSecurityConfig.securityScheme())).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.cast.treinamento.controller"))
				.paths(PathSelectors.any()).build();
	}

}

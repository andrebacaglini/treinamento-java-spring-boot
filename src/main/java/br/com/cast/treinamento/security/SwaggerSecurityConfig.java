package br.com.cast.treinamento.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

@Configuration
public class SwaggerSecurityConfig {

	@Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
	private String clienteId;

	@Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
	private String clienteSecret;

	/***
	 * Bean para injetar os valores do clientId e clientSecret no swagger-ui. Sem
	 * essa injeção, o swagger-ui ainda apresenta os campos, porém em branco.
	 * 
	 */
	@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder().clientId(clienteId).clientSecret(clienteSecret)
				.scopeSeparator(" ").useBasicAuthenticationWithAccessCodeGrant(true).build();
	}

	/***
	 * Configuração do esquema de segurança. Informa ao swagger quais são os
	 * escopos. esperados (definidos no AuthorizationConfig). Informa também quais
	 * são os grant types configurados (também no AuthorizationConfig).
	 * 
	 */
	public OAuth securityScheme() {

		List<GrantType> grantTypes = new ArrayList<>();
		GrantType password = new ResourceOwnerPasswordCredentialsGrant("http://localhost:8090/treinamento/oauth/token");
		// TODO: GrantType refreshToken = new RefreshTokenGranter("", null, null)

		grantTypes.add(password);

		return new OAuth("oauth2schema", escopoAutorizacaoPadrao(), grantTypes);

	}

	/***
	 * Metodo auxiliar para determinar os escopos gerenciados pela aplicação.
	 */
	private List<AuthorizationScope> escopoAutorizacaoPadrao() {
		List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
		authorizationScopeList.add(new AuthorizationScope("read", "Permissão de leitura."));
		authorizationScopeList.add(new AuthorizationScope("write", "Permissão de escrita."));
		return authorizationScopeList;
	}

	/***
	 * Configuração do contexto de segurança. Determina a relação do escopo de
	 * acesso (AuthorizationScope) pelos caminhos da aplicação (.forPaths())
	 */
	public SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(Collections.singletonList(new SecurityReference("oauth2schema",
						(AuthorizationScope[]) escopoAutorizacaoPadrao().toArray(new AuthorizationScope[0]))))
				.forPaths(PathSelectors.any()).build();
	}

}

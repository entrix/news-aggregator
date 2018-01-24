/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.bubbleinvestor.rest;

/**
 *
 * @author hantsy
 */
//@Configuration
//@EnableWebFluxSecurity
class SecurityConfig {
//
//	@Bean
//	SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
//		return http
//			.authorizeExchange()
//				.pathMatchers(HttpMethod.GET, "/posts/**").permitAll()
//                .pathMatchers(HttpMethod.DELETE, "/posts/**").hasRole("ADMIN")
//				//.pathMatchers("/users/{user}/**").access(this::currentUserMatchesPath)
//				.anyExchange().authenticated()
//				.and()
//			.build();
//	}
//
//	private Mono<AuthorizationDecision> currentUserMatchesPath(Mono<Authentication> authentication, AuthorizationContext context) {
//		return authentication
//			.map( a -> context.getVariables().get("user").equals(a.getName()))
//			.map( granted -> new AuthorizationDecision(granted));
//	}
//
//	@Bean
//	public MapReactiveUserDetailsService userDetailsRepository() {
//		UserDetails rob = User.withDefaultPasswordEncoder().username("test").password("password").roles("USER").build();
//		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("password").roles("USER","ADMIN").build();
//		return new MapReactiveUserDetailsService(rob, admin);
//	}

}

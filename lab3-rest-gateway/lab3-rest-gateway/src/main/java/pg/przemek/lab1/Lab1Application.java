package pg.przemek.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class Lab1Application
{

	public static void main(String[] args)
	{
		SpringApplication.run(Lab1Application.class, args);
	}

	@Bean
	public RouteLocator myRouteLocator(RouteLocatorBuilder routeLocatorBuilder)
	{
		return routeLocatorBuilder
				.routes()
				.route("departments", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/departments/{name}", "/api/departments")
						.uri("http://localhost:8081"))
				.route("doctors", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/doctors/**", "/api/doctors", "/api/departments/{name}/doctors", "/api/departments/{name}/doctors/**")
						.uri("http://localhost:8082"))
				.build();
	}

	@Bean
	public CorsWebFilter corsWebFilter() {

		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
		corsConfig.addAllowedHeader("*");

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}


}

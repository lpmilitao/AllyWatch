package br.com.AllyWatch.server.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import static br.com.AllyWatch.server.Domain.Enum.Role.Names.ADMIN;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(POST, "/login").permitAll()
                        .requestMatchers(POST, "/users").permitAll()
                        .requestMatchers(POST, "/specialists/**").permitAll()
                        .requestMatchers("/admin/*").hasAuthority(ADMIN)
                ).authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .logout().disable()
                .oauth2ResourceServer()
                    .jwt()
                        .jwtAuthenticationConverter(getJwtAutheticationConverter());
        return http.build();
    }

    private JwtAuthenticationConverter getJwtAutheticationConverter(){
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthoritiesClaimName("authorities");
        converter.setAuthorityPrefix("");

        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(converter);

        return authenticationConverter;
    }
}

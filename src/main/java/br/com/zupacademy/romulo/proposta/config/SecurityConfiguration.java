package br.com.zupacademy.romulo.proposta.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers(HttpMethod.GET, "/propostas/**").hasAuthority("SCOPE_proposal:read")
                        .antMatchers(HttpMethod.POST, "/propostas/**").hasAuthority("SCOPE_proposal:write")
                        .antMatchers(HttpMethod.GET, "/bloqueios/**").hasAuthority("SCOPE_proposal:read")
                        .antMatchers(HttpMethod.POST, "/bloqueios/**").hasAuthority("SCOPE_proposal:write")
                        .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/viagens/**").hasAuthority("SCOPE_proposal:write")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

}

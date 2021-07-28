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
                        .anyRequest().authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

}
/*
curl --location --request POST 'http://keycloak:8080/auth/realms/proposta/protocol/openid-connect/token' \
        --header 'Content-Type: application/x-www-form-urlencoded' \
        --data-urlencode 'grant_type=password' \
        --data-urlencode 'username=romulo' \
        --data-urlencode 'password=romulo' \
        --data-urlencode 'client_id=proposta-client' \
        --data-urlencode 'client_secret=dc1d832b-5733-44aa-bd24-926b1cbf2f20' \
        --data-urlencode 'proposal:write'

eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJoWXM1UVpuTE9RMS15OUNsNHhHNkowWVZQLVlpencyLVhpenlBa0F3WnlRIn0.eyJleHAiOjE2Mjc1MzMxMDMsImlhdCI6MTYyNzQ5NzEwMywianRpIjoiY2Y2ZTdiMDItZTAxOS00ZGE0LWEwYzYtYTRlYTlkZTI4MTg4IiwiaXNzIjoiaHR0cDovL2tleWNsb2FrOjgwODAvYXV0aC9yZWFsbXMvcHJvcG9zdGEiLCJzdWIiOiI4NjllY2U1Yi00Y2NhLTQ1NTQtODVhZi05ZjUyM2ZhOTU3YzEiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJwcm9wb3N0YS1jbGllbnQiLCJzZXNzaW9uX3N0YXRlIjoiM2IxN2FlNjAtNWZkMC00YzY5LWFjNTQtNTJhMDFjODVmYWJmIiwiYWNyIjoiMSIsInNjb3BlIjoicHJvcG9zYWw6d3JpdGUgcHJvcG9zYWw6cmVhZCJ9.glj8P4CpbbcdXSQo5NYmLwVgrOwUkwL_ihKDv2L9GVhq5XLkyDK8y9fN6XGHzZPveMswIFKG2LRfZVGwrSis3S2HqJ-3EQ4U7e4Z81l8-tHoKlVFhRwLvqVTzpnDQWDFMnTeJ6HaZ1fp9Xm1-xW2gkv61HUUtr6M6ZmbFkoN8C4NKjgKm96xtJsR-_qY4EITrO1bTKt1dd-ch6qk3mqYWQBYphRtwgorRHAODxVIpElEG_lDrZn4YA5XVyVoe2OrbN53-SSFCerFkbUpUzr8VeF3ckcl08vN38urTOryGHae-oaJRLSW3SzILKihQg8BJuIZS5PbuuOObe4ansWVfQ

curl --location --request post 'http://proposta:8080/propostas' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJoWXM1UVpuTE9RMS15OUNsNHhHNkowWVZQLVlpencyLVhpenlBa0F3WnlRIn0.eyJleHAiOjE2Mjc1MzMxMDMsImlhdCI6MTYyNzQ5NzEwMywianRpIjoiY2Y2ZTdiMDItZTAxOS00ZGE0LWEwYzYtYTRlYTlkZTI4MTg4IiwiaXNzIjoiaHR0cDovL2tleWNsb2FrOjgwODAvYXV0aC9yZWFsbXMvcHJvcG9zdGEiLCJzdWIiOiI4NjllY2U1Yi00Y2NhLTQ1NTQtODVhZi05ZjUyM2ZhOTU3YzEiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJwcm9wb3N0YS1jbGllbnQiLCJzZXNzaW9uX3N0YXRlIjoiM2IxN2FlNjAtNWZkMC00YzY5LWFjNTQtNTJhMDFjODVmYWJmIiwiYWNyIjoiMSIsInNjb3BlIjoicHJvcG9zYWw6d3JpdGUgcHJvcG9zYWw6cmVhZCJ9.glj8P4CpbbcdXSQo5NYmLwVgrOwUkwL_ihKDv2L9GVhq5XLkyDK8y9fN6XGHzZPveMswIFKG2LRfZVGwrSis3S2HqJ-3EQ4U7e4Z81l8-tHoKlVFhRwLvqVTzpnDQWDFMnTeJ6HaZ1fp9Xm1-xW2gkv61HUUtr6M6ZmbFkoN8C4NKjgKm96xtJsR-_qY4EITrO1bTKt1dd-ch6qk3mqYWQBYphRtwgorRHAODxVIpElEG_lDrZn4YA5XVyVoe2OrbN53-SSFCerFkbUpUzr8VeF3ckcl08vN38urTOryGHae-oaJRLSW3SzILKihQg8BJuIZS5PbuuOObe4ansWVfQ' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name" : "Veronica Mueller",
    "document" : "27268257708",
    "email": "luram.archanjo@zup.com.br",
    "salary" : 840.15,
    "address" : {
        "state" : "São Paulo",
        "city" : "Schambergerchester",
        "street" : "McDermott Field, 202"
    }
}'


*/
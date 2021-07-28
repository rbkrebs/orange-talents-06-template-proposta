package br.com.zupacademy.romulo.proposta.bloqueio;

import br.com.zupacademy.romulo.proposta.validadores.ValorUnico;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

public class BloqueioDto {


    @ValorUnico(entidade = "Bloqueio", atributo = "numeroCartao")
    @NotBlank
    @JsonProperty
    private String numeroCartao;

    @JsonCreator
    public BloqueioDto(@NotBlank String numeroCartao){
        this.numeroCartao = numeroCartao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public Bloqueio toModel(HttpServletRequest httpServletRequest) {

        String userAgent = httpServletRequest.getHeader("user-agent");
        String ip = getIp(httpServletRequest);
        Bloqueio bloqueio = new Bloqueio(ip,getNumeroCartao(), userAgent);

        return bloqueio;
    }

    private String getIp(HttpServletRequest httpServletRequest){

        String ip = httpServletRequest.getHeader("X-FORWARDED-FOR");

        if(ip!=null){

            return ip.contains(",") ? ip.split(",")[0]: ip;
        }
            return httpServletRequest.getRemoteAddr();

    }
}

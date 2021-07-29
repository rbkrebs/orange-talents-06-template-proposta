package br.com.zupacademy.romulo.proposta.viagem;

import br.com.zupacademy.romulo.proposta.validadores.ValorUnico;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ViagemDto {


    @NotBlank
    private String destinoViagem;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate terminoViagem;

    @JsonCreator
    public ViagemDto( String destinoViagem, LocalDate terminoViagem) {

        this.destinoViagem = destinoViagem;
        this.terminoViagem = terminoViagem;
    }


    public String getDestinoViagem() {
        return destinoViagem;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }

    public Viagem toModel(HttpServletRequest httpServletRequest, String numeroCartao) {

        String userAgent = httpServletRequest.getHeader("user-agent");
        String ip = getIp(httpServletRequest);
        Viagem viagem = new Viagem(ip,numeroCartao, userAgent, getDestinoViagem(), getTerminoViagem());

        return viagem;
    }

    private String getIp(HttpServletRequest httpServletRequest){

        String ip = httpServletRequest.getHeader("X-FORWARDED-FOR");

        if(ip!=null){

            return ip.contains(",") ? ip.split(",")[0]: ip;
        }
            return httpServletRequest.getRemoteAddr();

    }
}

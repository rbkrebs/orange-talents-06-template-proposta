package br.com.zupacademy.romulo.proposta.clients.cartao;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsultaCartaoResponse {

    @JsonProperty
    private String id;

    @JsonCreator
    public ConsultaCartaoResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

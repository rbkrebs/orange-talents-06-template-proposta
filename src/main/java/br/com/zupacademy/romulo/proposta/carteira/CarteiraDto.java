package br.com.zupacademy.romulo.proposta.carteira;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class CarteiraDto {

    @Email
    @JsonProperty
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @JsonProperty
    private EnumCarteira enumCarteira;

    @JsonCreator
    public CarteiraDto(String email, EnumCarteira enumCarteira) {
        this.email = email;
        this.enumCarteira = enumCarteira;
    }

    public EnumCarteira getEnumCarteira() {
        return enumCarteira;
    }

    public String getEmail() {
        return email;
    }

    public Carteira toMmodel(String numeroCartao) {
        return new Carteira(numeroCartao,this.email, this.enumCarteira);
    }
}

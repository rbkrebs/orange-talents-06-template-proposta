package br.com.zupacademy.romulo.proposta.biometria;

import br.com.zupacademy.romulo.proposta.validadores.ValidaBase64;
import br.com.zupacademy.romulo.proposta.validadores.ValorUnico;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class BiometriaDto {


    @ValidaBase64
    @NotBlank
    @JsonProperty
    @ValorUnico(entidade = "Biometria", atributo = "biometria", message = "Biometria já cadastrada")
    private String biometria;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BiometriaDto(String biometria) {
        this.biometria = biometria;
    }

    public Biometria toModel() {
        return new Biometria(this.biometria);
    }
}

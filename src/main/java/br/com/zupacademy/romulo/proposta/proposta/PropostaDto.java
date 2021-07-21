package br.com.zupacademy.romulo.proposta.proposta;

import br.com.zupacademy.romulo.proposta.validadores.ExisteRegistro;
import br.com.zupacademy.romulo.proposta.validadores.ValidaDocumento;
import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaDto {

    @ValidaDocumento
    @ExisteRegistro(entidade = "Proposta", atributo = "documento")
    private String documento;

    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @Positive
    @NotNull
    private BigDecimal salario;


    public PropostaDto(@ValidaDocumento String documento,
                       @Email String email,
                       @NotBlank String nome,
                       @NotBlank String endereco,
                       @Positive @NotNull BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Proposta toModel(PropostaDto propostaDto) {

        Proposta proposta = new Proposta(
                propostaDto.documento,
                propostaDto.email,
                propostaDto.nome,
                propostaDto.endereco,
                propostaDto.salario
        );

        return proposta;
    }
}

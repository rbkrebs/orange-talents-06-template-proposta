package br.com.zupacademy.romulo.proposta.proposta;

import br.com.zupacademy.romulo.proposta.clients.solicitacao.CondicaoSolicitacao;

import javax.persistence.*;
import javax.sound.sampled.Port;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Proposta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Column(nullable = false, unique = true)
    private String documento;
    @Email
    @Column(nullable = false)
    private String email;
    @NotBlank
    @Column(nullable = false)
    private String nome;
    @NotBlank
    @Column(nullable = false)
    private String endereco;
    @NotNull
    @Min(0)
    @Column(nullable = false)
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private CondicaoSolicitacao condicaoSolicitacao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(@NotBlank String documento,
                    @Email String email,
                    @NotBlank String nome,
                    @NotBlank String endereco,
                    @NotNull @Min(0) BigDecimal salario) {


        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return this.id;
    }

    public void setCondicaoSolicitacao(CondicaoSolicitacao condicaoSolicitacao) {
        this.condicaoSolicitacao = condicaoSolicitacao;
    }
}

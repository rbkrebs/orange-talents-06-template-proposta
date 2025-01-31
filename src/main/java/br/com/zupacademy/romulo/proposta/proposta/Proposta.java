package br.com.zupacademy.romulo.proposta.proposta;

import br.com.zupacademy.romulo.proposta.clients.solicitacao.CondicaoSolicitacao;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import javax.persistence.*;
import javax.sound.sampled.Port;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
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

    private String numeroDoCartao;

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


        this.documento =  this.criptografar().encrypt(documento);
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return this.id;
    }

    public String getDocumento() {

        return this.criptografar().decrypt(this.documento);
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public CondicaoSolicitacao getCondicaoSolicitacao() {
        return condicaoSolicitacao;
    }

    public void setCondicaoSolicitacao(CondicaoSolicitacao condicaoSolicitacao) {
        this.condicaoSolicitacao = condicaoSolicitacao;
    }

    public void setNumeroDoCartao(String numeroDoCartao) {
        this.numeroDoCartao = numeroDoCartao;
    }

    private TextEncryptor criptografar(){

       return Encryptors.queryableText("password", "5c0744940b5c369b");
    }

    @Override
    public String toString() {
        return "Proposta{" +
                "id=" + id +
                ", documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", salario=" + salario +
                ", numeroDoCartao='" + numeroDoCartao + '\'' +
                ", condicaoSolicitacao=" + condicaoSolicitacao +
                '}';
    }


}

package br.com.zupacademy.romulo.proposta.proposta;

import br.com.zupacademy.romulo.proposta.clients.solicitacao.CondicaoSolicitacao;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class PropostaResponse {


    @JsonProperty
    private Long id;
    @JsonProperty
    private String documento;
    @JsonProperty
    private String email;
    @JsonProperty
    private String nome;
    @JsonProperty
    private String endereco;
    @JsonProperty
    private BigDecimal salario;
    @JsonProperty
    private String numeroDoCartao;
    @JsonProperty
    private CondicaoSolicitacao condicaoSolicitacao;


    @JsonCreator
    public PropostaResponse(Long id, String documento, String email, String nome, String endereco, BigDecimal salario, String numeroDoCartao, CondicaoSolicitacao condicaoSolicitacao) {
        this.id = id;
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.numeroDoCartao = numeroDoCartao;
        this.condicaoSolicitacao = condicaoSolicitacao;
    }


    public static PropostaResponse fromModel(Proposta proposta) {

        return new PropostaResponse(proposta.getId(),
                proposta.getDocumento(),
                proposta.getEmail(),
                proposta.getNome(),
                proposta.getEndereco(),
                proposta.getSalario(),
                proposta.getNumeroDoCartao(),
                proposta.getCondicaoSolicitacao());
    }
}

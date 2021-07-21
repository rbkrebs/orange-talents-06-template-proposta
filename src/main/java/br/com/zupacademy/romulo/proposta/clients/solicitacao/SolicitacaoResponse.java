package br.com.zupacademy.romulo.proposta.clients.solicitacao;

public class SolicitacaoResponse {

    private String documento;
    private String nome;
    private CondicaoSolicitacao resultadoSolicitacao;
    private String idProposta;


    public SolicitacaoResponse(String documento, String nome, String resultadoSolicitacao, String idProposta) {

        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = CondicaoSolicitacao.converteEnum(resultadoSolicitacao);
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public CondicaoSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }
}

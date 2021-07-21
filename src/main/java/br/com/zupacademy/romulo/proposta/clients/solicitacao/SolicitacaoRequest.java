package br.com.zupacademy.romulo.proposta.clients.solicitacao;

public class SolicitacaoRequest {

    private String documento;
    private String nome;
    private String idProposta;


    public SolicitacaoRequest(String documento, String nome, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = String.valueOf(idProposta);
    }


    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}

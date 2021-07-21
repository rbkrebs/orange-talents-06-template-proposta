package br.com.zupacademy.romulo.proposta.clients.cartao;

public class ConsultaCartaoRequest {

    private String idProposta;

    public ConsultaCartaoRequest(String idProposta) {
        this.idProposta = idProposta;
    }

    public String getIdProposta() {
        return idProposta;
    }
}

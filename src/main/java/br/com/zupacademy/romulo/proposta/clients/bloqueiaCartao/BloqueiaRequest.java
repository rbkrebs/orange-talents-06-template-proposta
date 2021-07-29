package br.com.zupacademy.romulo.proposta.clients.bloqueiaCartao;

public class BloqueiaRequest {

    private String sistemaResponsavel;

    public BloqueiaRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}

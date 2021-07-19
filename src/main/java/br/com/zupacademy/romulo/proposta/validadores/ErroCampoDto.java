package br.com.zupacademy.romulo.proposta.validadores;

public class ErroCampoDto {

    private String campo;
    private String mensagem;

    public ErroCampoDto(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}

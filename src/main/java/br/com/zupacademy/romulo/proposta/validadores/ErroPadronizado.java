package br.com.zupacademy.romulo.proposta.validadores;

import java.util.Collection;

public class ErroPadronizado {

    private Collection<String> mensagens;

    public ErroPadronizado(Collection<String> mensagens) {

        this.mensagens = mensagens;
    }

    public Collection<String> getMensagens() {
        return this.mensagens;
    }


}

package br.com.zupacademy.romulo.proposta.clients.solicitacao;


import br.com.zupacademy.romulo.proposta.validadores.ApiErroException;
import org.springframework.http.HttpStatus;

public enum CondicaoSolicitacao {
    ELEGIVEL("SEM_RESTRICAO"), NAO_ELEGIVEL("COM_RESTRICAO");

    private String restricao;

    CondicaoSolicitacao(String restricao) {
        this.restricao = restricao;
    }

    static CondicaoSolicitacao converteEnum(String string){

        for(CondicaoSolicitacao condicaoSolicitacao: CondicaoSolicitacao.values()){
            if(condicaoSolicitacao.restricao.equalsIgnoreCase(string)){
                return condicaoSolicitacao;
            }

        }
        throw new ApiErroException(HttpStatus.INTERNAL_SERVER_ERROR, "Condição de Solicitação não encontrada");

    }
}

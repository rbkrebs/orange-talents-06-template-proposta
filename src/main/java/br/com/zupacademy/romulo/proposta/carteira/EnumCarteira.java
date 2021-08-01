package br.com.zupacademy.romulo.proposta.carteira;

import br.com.zupacademy.romulo.proposta.clients.solicitacao.CondicaoSolicitacao;
import br.com.zupacademy.romulo.proposta.validadores.ApiErroException;
import org.springframework.http.HttpStatus;

public enum EnumCarteira {

    PAYPAL("PAYPAL"), SAMSUNG_PAY("SAMSUNG PAY");;


    private String servico;

    EnumCarteira(String servico) {
        this.servico =  servico;
    }

    static EnumCarteira converteEnum(String string){

        for(EnumCarteira enumCarteira: EnumCarteira.values()){
            if(enumCarteira.servico.equalsIgnoreCase(string.toUpperCase())){
                return enumCarteira;
            }

        }
        throw new ApiErroException(HttpStatus.BAD_REQUEST, "Esta carteira não está habilitada");

    }

    @Override
    public String toString() {
        return this.servico;
    }
}

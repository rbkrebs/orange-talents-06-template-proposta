package br.com.zupacademy.romulo.proposta.clients.solicitacao;

import br.com.zupacademy.romulo.proposta.proposta.Proposta;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class AvaliaSolicitacao {

    private final Logger logger = LoggerFactory.getLogger(AvaliaSolicitacao.class);


    public void verificaDados(DadosFinanceiros dadosFinanceiros, EntityManager entityManager, Proposta proposta,SolicitacaoRequest solicitacaoRequest) {
        logger.info("Vericando dados financeiros ");

        try{
            SolicitacaoResponse solicitacaoResponse = dadosFinanceiros.verificar(solicitacaoRequest);
            proposta.setCondicaoSolicitacao(solicitacaoResponse.getResultadoSolicitacao());
            entityManager.merge(proposta);
            logger.info("Proposta com cliente elegível ");
        }catch (FeignException exception){
            proposta.setCondicaoSolicitacao(CondicaoSolicitacao.NAO_ELEGIVEL);
            entityManager.merge(proposta);
            logger.warn("Exceção: {}",exception);
            logger.info("Proposta com cliente não elegível ");
            //throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY,"Cliente não elegível");
        }
    }
}

package br.com.zupacademy.romulo.proposta.config;


import br.com.zupacademy.romulo.proposta.clients.cartao.ConsultaCartaoResponse;
import br.com.zupacademy.romulo.proposta.clients.cartao.ConsultaCartao;
import br.com.zupacademy.romulo.proposta.clients.cartao.ConsultaCartaoRequest;
import br.com.zupacademy.romulo.proposta.clients.solicitacao.CondicaoSolicitacao;
import br.com.zupacademy.romulo.proposta.proposta.Proposta;
import br.com.zupacademy.romulo.proposta.proposta.PropostaRepository;
import feign.FeignException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultadorCartao {

    private final Logger logger = LoggerFactory.getLogger(ConsultadorCartao.class);

    @Autowired
    ConsultaCartao consultaCartao;

    @Autowired
    PropostaRepository propostaRepository;

    @Scheduled(fixedDelayString = "${periodicidade-consulta-cartoes}")
    public void consularCartoes() {

        logger.info("Consultando cartões ");

        List<Proposta> listaDePropostas = propostaRepository.findByCondicaoSolicitacaoAndNumeroDoCartao(CondicaoSolicitacao.ELEGIVEL,null);

        listaDePropostas.forEach(proposta ->{
            String idProposta = String.valueOf(proposta.getId());
            try{
                ConsultaCartaoResponse consulta = consultaCartao.consularCartao(new ConsultaCartaoRequest(idProposta));
                proposta.setNumeroDoCartao(consulta.getId());
                propostaRepository.save(proposta);
                logger.info("Cartão {} associado à proposta {}",consulta.getId(), proposta.getId());
            }catch (FeignException e){
                logger.warn("Proposta {} ainda n]ao possui cartão associado", proposta.getId());
            }


        }  );


    }

}

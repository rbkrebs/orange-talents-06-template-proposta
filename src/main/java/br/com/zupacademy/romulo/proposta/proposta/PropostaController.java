package br.com.zupacademy.romulo.proposta.proposta;


import br.com.zupacademy.romulo.proposta.clients.solicitacao.CondicaoSolicitacao;
import br.com.zupacademy.romulo.proposta.clients.solicitacao.DadosFinanceiros;
import br.com.zupacademy.romulo.proposta.clients.solicitacao.SolicitacaoRequest;
import br.com.zupacademy.romulo.proposta.clients.solicitacao.SolicitacaoResponse;
import br.com.zupacademy.romulo.proposta.validadores.ApiErroException;
import com.google.gson.Gson;
import feign.Feign;
import feign.FeignException;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    DadosFinanceiros dadosFinanceiros;


    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PropostaDto propostaDto, UriComponentsBuilder uriComponentsBuilder){

        Proposta proposta = propostaDto.toModel(propostaDto);
        entityManager.persist(proposta);

        SolicitacaoRequest solicitacaoRequest = new SolicitacaoRequest(propostaDto.getDocumento(),
                propostaDto.getNome(), proposta.getId());
        try{
            SolicitacaoResponse solicitacaoResponse = dadosFinanceiros.verificar(solicitacaoRequest);
            proposta.setCondicaoSolicitacao(solicitacaoResponse.getResultadoSolicitacao());
            entityManager.merge(proposta);
        }catch (FeignException exception){
            proposta.setCondicaoSolicitacao(CondicaoSolicitacao.NAO_ELEGIVEL);
            entityManager.merge(proposta);

            //throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY,"Cliente não elegível");
        }
        return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri()).build();
    }

}

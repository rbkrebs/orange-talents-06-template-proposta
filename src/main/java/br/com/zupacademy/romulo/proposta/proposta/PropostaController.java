package br.com.zupacademy.romulo.proposta.proposta;


import br.com.zupacademy.romulo.proposta.clients.solicitacao.*;
import br.com.zupacademy.romulo.proposta.validadores.ApiErroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    DadosFinanceiros dadosFinanceiros;

    @Autowired
    AvaliaSolicitacao avaliaSolicitacao;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PropostaDto propostaDto, UriComponentsBuilder uriComponentsBuilder){

        logger.info("Cadastro de proposta ");

        Proposta proposta = propostaDto.toModel(propostaDto);
        entityManager.persist(proposta);

        SolicitacaoRequest solicitacaoRequest = new SolicitacaoRequest(propostaDto.getDocumento(),
                propostaDto.getNome(), proposta.getId());

        avaliaSolicitacao.verificaDados(dadosFinanceiros, entityManager,proposta, solicitacaoRequest);

        return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri()).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> acompanhaProposta(@PathVariable("id") Long idProposta){

        logger.info("Consulta de proposta com id {}", idProposta);

        Proposta proposta = entityManager.find(Proposta.class,idProposta);

        if(proposta!=null){

            PropostaResponse propostaResponse = PropostaResponse.fromModel(proposta);

            return ResponseEntity.ok().body(propostaResponse);
        }
        throw new ApiErroException(HttpStatus.NOT_FOUND, "Essa proposta não existe");
    }

}

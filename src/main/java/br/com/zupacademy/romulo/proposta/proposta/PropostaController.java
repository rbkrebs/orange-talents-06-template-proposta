package br.com.zupacademy.romulo.proposta.proposta;


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

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PropostaDto propostaDto, UriComponentsBuilder uriComponentsBuilder){

        Long id = propostaDto.salvar(propostaDto, entityManager);

        return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(id).toUri()).build();
    }

}

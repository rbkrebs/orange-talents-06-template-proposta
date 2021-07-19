package br.com.zupacademy.romulo.proposta.proposta;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PropostaDto propostaDto){

        propostaDto.salvar(propostaDto, entityManager);

        return ResponseEntity.ok().build();
    }

}

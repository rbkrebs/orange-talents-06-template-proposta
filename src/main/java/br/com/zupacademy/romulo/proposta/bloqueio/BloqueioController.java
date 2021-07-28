package br.com.zupacademy.romulo.proposta.bloqueio;


import br.com.zupacademy.romulo.proposta.proposta.Proposta;
import br.com.zupacademy.romulo.proposta.proposta.PropostaRepository;
import br.com.zupacademy.romulo.proposta.validadores.ApiErroException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/bloqueios")
public class BloqueioController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PropostaRepository propostaRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<?> bloqueia(@Valid @RequestBody BloqueioDto numeroCartao, HttpServletRequest httpServletRequest){

        Optional<Proposta> existeCartao = propostaRepository.findByNumeroDoCartao(numeroCartao.getNumeroCartao());
        if(existeCartao.isEmpty()){
            throw new ApiErroException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
        }
        Bloqueio bloqueio = numeroCartao.toModel(httpServletRequest);

        entityManager.persist(bloqueio);

        return ResponseEntity.ok().build();

    }
}

package br.com.zupacademy.romulo.proposta.viagem;

import br.com.zupacademy.romulo.proposta.proposta.Proposta;
import br.com.zupacademy.romulo.proposta.proposta.PropostaRepository;
import br.com.zupacademy.romulo.proposta.validadores.ApiErroException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController
@RequestMapping("/viagens")
@Validated
public class ViagemController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PropostaRepository propostaRepository;


    @PostMapping("/{numeroCartao}")
    @Transactional
    public ResponseEntity<?> bloqueia(@PathVariable("numeroCartao")
                                          @NotBlank String numeroCartao,
                                          @Valid @RequestBody ViagemDto viagemDto,
                                          HttpServletRequest httpServletRequest){

        Optional<Proposta> existeCartao = propostaRepository.findByNumeroDoCartao(numeroCartao);
        if(existeCartao.isEmpty()){
            throw new ApiErroException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
        }

        Viagem viagem = viagemDto.toModel(httpServletRequest, numeroCartao);

        entityManager.persist(viagem);

        return ResponseEntity.ok().build();

    }
}

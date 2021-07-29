package br.com.zupacademy.romulo.proposta.bloqueio;


import br.com.zupacademy.romulo.proposta.clients.bloqueiaCartao.BloqueiaCartao;
import br.com.zupacademy.romulo.proposta.clients.bloqueiaCartao.BloqueiaRequest;
import br.com.zupacademy.romulo.proposta.proposta.Proposta;
import br.com.zupacademy.romulo.proposta.proposta.PropostaRepository;
import br.com.zupacademy.romulo.proposta.validadores.ApiErroException;
import br.com.zupacademy.romulo.proposta.validadores.ValorUnico;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController
@RequestMapping("/bloqueios")
@Validated
public class BloqueioController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private BloqueiaCartao bloqueiaCartao;


    @PostMapping("{numeroCartao}")
    @Transactional
    public ResponseEntity<?> bloqueia(@PathVariable("numeroCartao")
                                          @ValorUnico(entidade = "Bloqueio", atributo = "numeroCartao")
                                          @NotBlank String numeroCartao,
                                      HttpServletRequest httpServletRequest){

        Optional<Proposta> existeCartao = propostaRepository.findByNumeroDoCartao(numeroCartao);
        if(existeCartao.isEmpty()){
            throw new ApiErroException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
        }
        BloqueioDto bloqueioDto =  new BloqueioDto( numeroCartao);
        try{
            bloqueiaCartao.bloquearCartao(numeroCartao,
                    new BloqueiaRequest("Sistema de Propostas"));
            Bloqueio bloqueio = bloqueioDto.toModel(httpServletRequest);

            entityManager.persist(bloqueio);

            return ResponseEntity.ok().build();
        }catch (FeignException exception){
            throw new ApiErroException(HttpStatus.BAD_REQUEST, "Falha ao tentar bloquear o cartão");
        }




    }
}

package br.com.zupacademy.romulo.proposta.viagem;

import br.com.zupacademy.romulo.proposta.bloqueio.Bloqueio;
import br.com.zupacademy.romulo.proposta.clients.bloqueiaCartao.BloqueiaRequest;
import br.com.zupacademy.romulo.proposta.clients.informaViagem.InformaViagem;
import br.com.zupacademy.romulo.proposta.clients.informaViagem.InformaViagemRequest;
import br.com.zupacademy.romulo.proposta.config.ConsultadorCartao;
import br.com.zupacademy.romulo.proposta.proposta.Proposta;
import br.com.zupacademy.romulo.proposta.proposta.PropostaRepository;
import br.com.zupacademy.romulo.proposta.validadores.ApiErroException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(ViagemController.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private InformaViagem informaViagem;


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

        try{

            informaViagem.informarViagem(numeroCartao,
                    new InformaViagemRequest(viagemDto.getDestinoViagem(), viagemDto.getTerminoViagem()));
            Viagem viagem = viagemDto.toModel(httpServletRequest, numeroCartao);

            entityManager.persist(viagem);

            return ResponseEntity.ok().build();
        }catch (FeignException exception){
            logger.warn("{}",exception);
            throw new ApiErroException(HttpStatus.BAD_REQUEST, "Falha ao tentar informar viagem");
        }


    }
}

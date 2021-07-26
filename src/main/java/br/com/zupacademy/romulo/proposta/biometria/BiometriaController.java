package br.com.zupacademy.romulo.proposta.biometria;

import br.com.zupacademy.romulo.proposta.proposta.Proposta;
import br.com.zupacademy.romulo.proposta.proposta.PropostaController;
import br.com.zupacademy.romulo.proposta.proposta.PropostaRepository;
import br.com.zupacademy.romulo.proposta.validadores.ApiErroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/biometrias")
public class BiometriaController {

    private final Logger logger = LoggerFactory.getLogger(BiometriaController.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PropostaRepository propostaRepository;


    @PostMapping("/{idProposta}")
    @Transactional
    public ResponseEntity<?> cadastrar(@PathVariable("idProposta") Long idProposta,
                                       @RequestBody @Valid BiometriaDto biometriaDto){

        logger.info("Biometria : {}", biometriaDto);

        Optional<Proposta> proposta = propostaRepository.findById(idProposta);

        logger.info("proposta : {}", proposta.toString());
        if(proposta.isPresent()){
            Biometria biometria = biometriaDto.toModel();
            biometria.setProposta(proposta.get());
            entityManager.persist(biometria);
            return ResponseEntity.ok().build();
        }

        throw new ApiErroException(HttpStatus.NOT_FOUND, "Proposta não encotrada");

    }

}

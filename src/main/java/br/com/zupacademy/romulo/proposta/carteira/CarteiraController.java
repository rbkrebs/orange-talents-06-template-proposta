package br.com.zupacademy.romulo.proposta.carteira;


import br.com.zupacademy.romulo.proposta.clients.associaCarteira.AssociaCarteira;
import br.com.zupacademy.romulo.proposta.clients.associaCarteira.AssociaCarteiraRequest;
import br.com.zupacademy.romulo.proposta.proposta.Proposta;
import br.com.zupacademy.romulo.proposta.proposta.PropostaController;
import br.com.zupacademy.romulo.proposta.proposta.PropostaRepository;
import br.com.zupacademy.romulo.proposta.validadores.ApiErroException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private AssociaCarteira associaCarteira;

    private final Logger logger = LoggerFactory.getLogger(CarteiraController.class);

    @PostMapping("/{numeroCartao}")
    @Transactional
    public ResponseEntity<?> adicionaCarteira(@PathVariable("numeroCartao") String numeroCartao,
                                                @Valid @RequestBody CarteiraDto carteiraDto){

        Optional<Proposta> proposta = propostaRepository.findByNumeroDoCartao(numeroCartao);

        if(proposta.isEmpty()){
            throw new ApiErroException(HttpStatus.NOT_FOUND, "Número de cartão inválido");
        }
        Optional<Carteira> carteiraOptional = carteiraRepository.findByNumeroCartaoAndServicoCarteira(numeroCartao, carteiraDto.getEnumCarteira());

        if(carteiraOptional.isEmpty()){
            try{
                Carteira carteira =  carteiraDto.toMmodel(numeroCartao);
                logger.warn("{}",carteira);
                associaCarteira.associaCarteira(numeroCartao, new AssociaCarteiraRequest(carteiraDto.getEmail(), carteiraDto.getEnumCarteira().toString()));
                carteiraRepository.save(carteira);
                return ResponseEntity.ok().build();
            }catch (FeignException exception){
                logger.warn("{}",exception);

                throw new ApiErroException(HttpStatus.BAD_REQUEST, "Falha ao associar o cartão a uma carteira");
            }

        }
        throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já associado a essa carteira");

    }

}

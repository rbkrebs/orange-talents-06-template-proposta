package br.com.zupacademy.romulo.proposta.clients.informaViagem;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@FeignClient(name ="informa-viagem" , url = "${api-cartoes}")
@Validated
public interface InformaViagem {


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json",value ="/{id}/avisos" )
    ResponseEntity<?> informarViagem(@PathVariable("id") @NotBlank String numeroCartao, @Valid @RequestBody InformaViagemRequest informaViagemRequest);

}

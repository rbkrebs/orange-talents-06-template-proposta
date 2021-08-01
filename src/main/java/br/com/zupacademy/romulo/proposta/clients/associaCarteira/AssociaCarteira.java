package br.com.zupacademy.romulo.proposta.clients.associaCarteira;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@FeignClient(name ="associa-carteira" , url = "${api-cartoes}")
@Validated
public interface AssociaCarteira {



    @RequestMapping(method = RequestMethod.POST, consumes = "application/json",value ="/{id}/carteiras" )
    ResponseEntity<?> associaCarteira(@PathVariable("id") @NotBlank String numeroCartao, @Valid @RequestBody AssociaCarteiraRequest associaCarteiraRequest);

}

package br.com.zupacademy.romulo.proposta.clients.bloqueiaCartao;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@FeignClient(name ="bloqueia-cartao" , url = "${api-cartoes}")
@Validated
public interface BloqueiaCartao {


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json",value ="/{id}/bloqueios" )
    ResponseEntity<?> bloquearCartao(@PathVariable("id") @NotBlank String numeroCartao, @Valid @RequestBody BloqueiaRequest sistemaSolicitante);

}

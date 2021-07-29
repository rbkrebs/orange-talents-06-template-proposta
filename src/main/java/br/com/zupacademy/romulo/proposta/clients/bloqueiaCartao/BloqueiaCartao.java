package br.com.zupacademy.romulo.proposta.clients.bloqueiaCartao;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name ="bloqueia-cartao" , url = "${api-cartoes}")
public interface BloqueiaCartao {


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json",value ="/{id}/bloqueios" )
    ResponseEntity<?> bloquearCartao(@PathVariable("id") String numeroCartao, @RequestBody BloqueiaRequest sistemaSolicitante);

}

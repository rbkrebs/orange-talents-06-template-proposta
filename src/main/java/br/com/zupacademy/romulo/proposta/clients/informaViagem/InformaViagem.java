package br.com.zupacademy.romulo.proposta.clients.informaViagem;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name ="informa-viagem" , url = "${api-cartoes}")
public interface InformaViagem {


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json",value ="/{id}/avisos" )
    ResponseEntity<?> informarViagem(@PathVariable("id") String numeroCartao, @RequestBody InformaViagemRequest informaViagemRequest);

}

package br.com.zupacademy.romulo.proposta.clients.cartao;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name ="verificaCartao" , url = "${api-cartoes}")
public interface ConsultaCartao {


    @RequestMapping(method = RequestMethod.GET, consumes = "application/json")
    ConsultaCartaoResponse consularCartao(@RequestBody ConsultaCartaoRequest consultaCartaoRequest);

}

package br.com.zupacademy.romulo.proposta.clients.solicitacao;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="solicitacao-financeiras", url = "${api-solicitacao}")
public interface DadosFinanceiros {

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    SolicitacaoResponse verificar(@RequestBody SolicitacaoRequest solicitacaoRequest);

}

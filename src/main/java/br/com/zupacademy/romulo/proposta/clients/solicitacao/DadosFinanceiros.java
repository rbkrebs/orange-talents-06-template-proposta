package br.com.zupacademy.romulo.proposta.clients.solicitacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("solicitacoes")
public interface DadosFinanceiros {

    @RequestMapping("/api/solicitacao")

}

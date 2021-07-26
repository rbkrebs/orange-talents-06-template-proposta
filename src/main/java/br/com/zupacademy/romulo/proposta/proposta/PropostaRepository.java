package br.com.zupacademy.romulo.proposta.proposta;

import br.com.zupacademy.romulo.proposta.clients.solicitacao.CondicaoSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    List<Proposta> findByCondicaoSolicitacaoAndNumeroDoCartao(CondicaoSolicitacao condicaoSolicitacao, String numero);
}
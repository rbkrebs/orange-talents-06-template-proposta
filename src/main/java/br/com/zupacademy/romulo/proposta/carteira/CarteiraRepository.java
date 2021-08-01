package br.com.zupacademy.romulo.proposta.carteira;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarteiraRepository extends CrudRepository<Carteira, Long> {

    Optional<Carteira> findByNumeroCartaoAndServicoCarteira(String numeroCartao, EnumCarteira enumCarteira);
}

package br.com.zupacademy.romulo.proposta.proposta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Proposta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private final String documento;
    private final String email;
    private final String nome;
    private final String endereco;
    private final BigDecimal salario;


    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {


        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return this.id;
    }
}

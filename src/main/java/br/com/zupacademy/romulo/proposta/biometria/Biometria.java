package br.com.zupacademy.romulo.proposta.biometria;

import br.com.zupacademy.romulo.proposta.proposta.Proposta;

import javax.persistence.*;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String biometria;

    @Deprecated
    Biometria(){}

    @ManyToOne
    private Proposta proposta;

    public Biometria(String biometria) {
        this.biometria = biometria;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }
}

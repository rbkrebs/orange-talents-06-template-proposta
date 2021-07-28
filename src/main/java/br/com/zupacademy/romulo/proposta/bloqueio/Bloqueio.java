package br.com.zupacademy.romulo.proposta.bloqueio;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant dataBloqueio;

    @NotBlank
    @Column(nullable = false)
    private String ip;

    @NotBlank
    @Column(nullable = false, unique = true)
    private  String numeroCartao;

    @NotBlank
    @Column(nullable = false)
    private String userAgent;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String ip, String numeroCartao, String userAgent) {
        this.ip = ip;
        this.numeroCartao = numeroCartao;
        this.userAgent = userAgent;
    }
}

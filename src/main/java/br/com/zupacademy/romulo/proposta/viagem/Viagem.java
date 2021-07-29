package br.com.zupacademy.romulo.proposta.viagem;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.time.LocalDate;

@Entity
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant avisoViagem;

    @NotBlank
    @Column(nullable = false)
    private String ip;

    @NotBlank
    @Column(nullable = false, unique = true)
    private  String numeroCartao;

    @NotBlank
    @Column(nullable = false)
    private String userAgent;

    @NotBlank
    @Column(nullable = false)
    private String destinoViagem;


    @Future
    @Column(nullable = false)
    private LocalDate terminoViagem;

    @Deprecated
    public Viagem() {
    }

    public Viagem( String ip, String numeroCartao, String userAgent, String destinoViagem, LocalDate terminoViagem) {

        this.ip = ip;
        this.numeroCartao = numeroCartao;
        this.userAgent = userAgent;
        this.destinoViagem = destinoViagem;
        this.terminoViagem = terminoViagem;
    }
}

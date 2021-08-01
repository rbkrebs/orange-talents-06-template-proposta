package br.com.zupacademy.romulo.proposta.carteira;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String numeroCartao;


    @Email
    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumCarteira servicoCarteira;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String numeroCartao, String email, EnumCarteira servicoCarteira) {
        this.numeroCartao = numeroCartao;
        this.email = email;
        this.servicoCarteira = servicoCarteira;
    }
}

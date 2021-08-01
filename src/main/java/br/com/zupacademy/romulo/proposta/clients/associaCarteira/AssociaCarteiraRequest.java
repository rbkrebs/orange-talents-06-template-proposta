package br.com.zupacademy.romulo.proposta.clients.associaCarteira;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;

public class AssociaCarteiraRequest {

    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    private String carteira;

    public AssociaCarteiraRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}

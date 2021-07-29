package br.com.zupacademy.romulo.proposta.clients.informaViagem;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class InformaViagemRequest {

    private String destino;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate validoAte;

    public InformaViagemRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}

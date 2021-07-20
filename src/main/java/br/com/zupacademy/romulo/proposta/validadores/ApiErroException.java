package br.com.zupacademy.romulo.proposta.validadores;

import org.springframework.http.HttpStatus;

public class ApiErroException extends RuntimeException {

    private final HttpStatus httpStatus;

    private final String reason;

    public ApiErroException(HttpStatus httpStatus, String reason) {

        this.httpStatus = httpStatus;
        this.reason = reason;
    }

    public String getReason() {

        return this.reason;
    }

    public HttpStatus getHttpStatus() {

        return this.httpStatus;
    }
}

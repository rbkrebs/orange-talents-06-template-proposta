package br.com.zupacademy.romulo.proposta.validadores;


import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;

public class VerificaBase64 implements ConstraintValidator<ValidaBase64, String> {


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        try{
            Base64.getDecoder().decode(s);
            return true;
        }catch (IllegalArgumentException exception){
            throw new ApiErroException(HttpStatus.BAD_REQUEST, "Biometria inválida");
        }

    }
}

package br.com.zupacademy.romulo.proposta.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VerificaDocumento implements ConstraintValidator<ValidaDocumento,String> {

    @Override
    public void initialize(ValidaDocumento constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}

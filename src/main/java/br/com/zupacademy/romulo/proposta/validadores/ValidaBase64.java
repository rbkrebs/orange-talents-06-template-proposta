package br.com.zupacademy.romulo.proposta.validadores;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VerificaBase64.class)
public @interface ValidaBase64 {

    String message() default "Biometria inválida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    String value() default "";
}

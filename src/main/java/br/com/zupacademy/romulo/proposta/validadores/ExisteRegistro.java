package br.com.zupacademy.romulo.proposta.validadores;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VerificaRegistro.class)
public @interface ExisteRegistro {

    String message() default "Informação já cadastrada no banco de dados";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    String value() default "";

    String entidade() default "";

    String atributo() default "";
}

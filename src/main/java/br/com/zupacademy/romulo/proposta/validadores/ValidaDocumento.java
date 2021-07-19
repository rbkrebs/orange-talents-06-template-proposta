package br.com.zupacademy.romulo.proposta.validadores;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@CNPJ
@CPF
@ConstraintComposition(CompositionType.OR)
@ReportAsSingleViolation
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface ValidaDocumento {

    String message() default "Documento inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    String value() default "";

    String entidade() default "";

    String atributo() default "";
}

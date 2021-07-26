package br.com.zupacademy.romulo.proposta.validadores;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorGenerico implements ConstraintValidator<ValorUnico, String> {

    @Autowired
    private EntityManager em;

    private String atributo;

    private String tabela;


    @Override
    public void initialize(ValorUnico constraintAnnotation) {

        this.atributo = constraintAnnotation.atributo();
        this.tabela = constraintAnnotation.entidade();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        Query query = em.createQuery("SELECT s FROM "+this.tabela+" s where "+this.atributo+"=:s").setParameter("s", s);

        return query.getResultList().isEmpty();


    }
}

package br.com.zupacademy.romulo.proposta.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VerificaRegistro implements ConstraintValidator<ExisteRegistro, String> {

    @Autowired
    private EntityManager em;

    private String atributo;

    private String tabela;

    @Override
    public void initialize(ExisteRegistro constraintAnnotation) {

        this.atributo = constraintAnnotation.atributo();
        this.tabela = constraintAnnotation.entidade();
        ConstraintValidator.super.initialize(constraintAnnotation);


    }

    @Override
    public boolean isValid(String atributo, ConstraintValidatorContext constraintValidatorContext) {

        Query query = em.createQuery("SELECT s FROM "+this.tabela+" s where "+this.atributo+"= :atributo")
                .setParameter("atributo",atributo);
        if(!query.getResultList().isEmpty()){
            throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Esse requisitante já solicitou uma proposta");

        }
        return true;
    }

}

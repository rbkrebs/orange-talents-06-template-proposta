package br.com.zupacademy.romulo.proposta.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroCampoDto> handle(MethodArgumentNotValidException exception){

        List<ErroCampoDto> erroFormAutorDtoList = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(erro -> {

            String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
            ErroCampoDto erroDto = new ErroCampoDto(erro.getField(), mensagem);
            erroFormAutorDtoList.add(erroDto);

        });

        return erroFormAutorDtoList;


    }
}
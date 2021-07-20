package br.com.zupacademy.romulo.proposta.validadores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestControllerAdvice
public class ErroValidacaoHandler {

    @Autowired
    private MessageSource messageSource;


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadronizado> handleBadRequest(MethodArgumentNotValidException exception){

        Collection<String> mensagensDeErros = new ArrayList();


        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(erro -> {

            String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());

            mensagensDeErros.add(mensagem);

        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroPadronizado(mensagensDeErros));


    }

    @ExceptionHandler(ApiErroException.class)
    public ResponseEntity<ErroPadronizado> handleApiErroException(ApiErroException apiErroException) {
        Collection<String> mensagensErros = new ArrayList<>();
        mensagensErros.add(apiErroException.getReason());

        ErroPadronizado erroPadronizado = new ErroPadronizado(mensagensErros);
        
        return ResponseEntity.status(apiErroException.getHttpStatus()).body(erroPadronizado);
    }
}
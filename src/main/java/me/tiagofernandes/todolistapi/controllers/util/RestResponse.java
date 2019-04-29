package me.tiagofernandes.todolistapi.controllers.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Locale;

public class RestResponse {
    private static final ResponseEntity NO_CONTENT_RESPONSE_ENTITY = new ResponseEntity(HttpStatus.NO_CONTENT);
    private static final ResponseEntity NOT_FOUND_RESPONSE_ENTITY = new ResponseEntity(HttpStatus.NOT_FOUND);

    private static final String SUCESSO_TITLE = "Sucesso!";
    private static final String SUCESSO_MSG = "Opera\u00E7\u00E3o realizada com sucesso!";
    private static final String TITLE = "title";
    private static final String MESSAGE = "message";

    public static <T> ResponseEntity<T> noContentOrNotFound(T t) {
        if (null == t) {
            return NOT_FOUND_RESPONSE_ENTITY;
        } else {
            return NO_CONTENT_RESPONSE_ENTITY;
        }
    }

    public static <T extends Iterable> ResponseEntity<T> createdOrNoContent(T t) {
        if (t.iterator().hasNext()) {
            return created(t);
        } else {
            return NO_CONTENT_RESPONSE_ENTITY;
        }
    }

    public static <T> ResponseEntity<T> createdOrNoContent(T t) {
        if (null == t) {
            return NO_CONTENT_RESPONSE_ENTITY;
        } else {
            return created(t);
        }
    }

    private static <T> ResponseEntity<T> created(T t) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(TITLE, SUCESSO_TITLE);
        headers.add(MESSAGE, SUCESSO_MSG);

        return new ResponseEntity(t, headers, HttpStatus.CREATED);
    }

    public static <T extends Iterable> ResponseEntity<T> createdOrNoContent(T t, MessageSource messageSource, String codigoMensagem, String... args) {
        if (t.iterator().hasNext()) {
            return created(t, messageSource, codigoMensagem, args);
        } else {
            return NO_CONTENT_RESPONSE_ENTITY;
        }
    }

    private static <T> ResponseEntity<T> created(T t, MessageSource messageSource, String codigoMensagem, String... args) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Locale locale = LocaleContextHolder.getLocale();
        headers.add(TITLE, SUCESSO_TITLE);
        String message = messageSource.getMessage(codigoMensagem, args, SUCESSO_MSG, locale);
        headers.add(MESSAGE, message);

        return new ResponseEntity(t, headers, HttpStatus.OK);
    }

    public static <T extends Iterable> ResponseEntity<T> createdOrNoContent(T t, MessageSource messageSource, String codigoTitulo, String codigoMensagem, String... args) {
        if (t.iterator().hasNext()) {
            return created(t, messageSource, codigoTitulo, codigoMensagem, args);
        } else {
            return NO_CONTENT_RESPONSE_ENTITY;
        }
    }

    private static <T> ResponseEntity<T> created(T t, MessageSource messageSource, String codigoTitulo, String codigoMensagem, String... args) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Locale locale = LocaleContextHolder.getLocale();
        String title = messageSource.getMessage(codigoTitulo, null, SUCESSO_TITLE, locale);
        headers.add(TITLE, title);
        String message = messageSource.getMessage(codigoMensagem, args, SUCESSO_MSG, locale);
        headers.add(MESSAGE, message);

        return new ResponseEntity(t, headers, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> okOrNotFound(T t) {
        if (null == t) {
            return NOT_FOUND_RESPONSE_ENTITY;
        } else {
            return okWithoutMessage(t);
        }
    }

    public static <T extends Iterable> ResponseEntity<T> okOrNotFound(T t) {
        if (null == t || !t.iterator().hasNext()) {
            return NOT_FOUND_RESPONSE_ENTITY;
        } else {
            return okWithoutMessage(t);
        }
    }

    public static <T> ResponseEntity<T> ok(T t) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(MESSAGE, SUCESSO_MSG);
        return new ResponseEntity(t, headers, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> created() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(MESSAGE, SUCESSO_MSG);
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<T> ok() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(MESSAGE, SUCESSO_MSG);
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> okWithoutMessage(T t) {
        return new ResponseEntity(t, HttpStatus.OK);
    }
}

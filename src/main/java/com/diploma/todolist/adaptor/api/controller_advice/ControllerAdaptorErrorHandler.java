package com.diploma.todolist.adaptor.api.controller_advice;

import com.diploma.todolist.config.constant.MDCKey;
import com.diploma.todolist.service.exceptions.error_code.AbstractErrorCodeException;
import com.diploma.todolist.service.exceptions.error_code.BadRequestException;
import com.diploma.todolist.service.exceptions.error_code.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestControllerAdvice
@Slf4j
public class ControllerAdaptorErrorHandler {


    private static final String NO_MESSAGE_AVAILABLE = "No message available";

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiErrorResponse handleConstraintViolationException(ConstraintViolationException e, WebRequest request) {

        final var errorResponse = new ApiErrorResponse();

        final var START_MESSAGE = "Constraint violation: ";

        final Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        final List<String> messages = new ArrayList<>(constraintViolations.size());
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            final String wrongValue = ((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().getName();
            final String violationMessage = constraintViolation.getMessage();

            final var message = String.format("'%s' %s", wrongValue, violationMessage);
            messages.add(message);
        }


        final var joinMessages = String.join(";", messages);
        final var message = START_MESSAGE + joinMessages;
        errorResponse.setMessage(message);

        errorResponse.setTimestamp(Instant.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());


        errorResponse.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        errorResponse.setXRequestId(MDC.get(MDCKey.X_REQUEST_ID));
        errorResponse.setCode("constraint_violation");

        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, WebRequest request) {
        final var errorResponse = new ApiErrorResponse();
        errorResponse.setTimestamp(Instant.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        errorResponse.setMessage("Invalid type for value " + e.getValue() + " of the " + e.getName() + " argument");
        errorResponse.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        errorResponse.setXRequestId(MDCKey.X_REQUEST_ID);
        errorResponse.setCode("method_argument_type_mismatch");

        return errorResponse;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {

        final var bindingResult = e.getBindingResult();
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        final var START_MESSAGE = "Method argument not valid: ";
        final List<String> messages = new ArrayList<>(fieldErrors.size());
        for (FieldError fieldError : fieldErrors) {
            final String wrongValue = fieldError.getField();
            final String violationMessage = fieldError.getDefaultMessage();

            final var message = String.format("'%s' %s", wrongValue, violationMessage);
            messages.add(message);
        }

        final var errorResponse = new ApiErrorResponse();
        final var joinMessages = String.join(";", messages);
        final var message = START_MESSAGE + joinMessages;
        errorResponse.setMessage(message);

        errorResponse.setTimestamp(Instant.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        errorResponse.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        errorResponse.setXRequestId(MDCKey.X_REQUEST_ID);
        errorResponse.setCode("method_argument_not_valid");

        return errorResponse;
    }


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiErrorResponse handleAbstractErrorCodeException(BadRequestException e, WebRequest request) {
        final var errorResponse = new ApiErrorResponse();
        errorResponse.setTimestamp(Instant.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(Optional.ofNullable(e.getMessage()).orElse(NO_MESSAGE_AVAILABLE));
        errorResponse.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        errorResponse.setXRequestId(MDC.get(MDCKey.X_REQUEST_ID));
        errorResponse.setCode(e.getCode());

        return errorResponse;
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ApiErrorResponse handleAbstractErrorCodeException(NotFoundException e, WebRequest request) {
        final var errorResponse = new ApiErrorResponse();
        errorResponse.setTimestamp(Instant.now());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(Optional.ofNullable(e.getMessage()).orElse(NO_MESSAGE_AVAILABLE));
        errorResponse.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        errorResponse.setXRequestId(MDC.get(MDCKey.X_REQUEST_ID));
        errorResponse.setCode(e.getCode());

        return errorResponse;
    }


    @ExceptionHandler(UncategorizedSQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ApiErrorResponse handleUncategorizedSQLException(UncategorizedSQLException e, WebRequest request) {
        log.error(e.getMessage(), e);
        final var errorResponse = new ApiErrorResponse();
        errorResponse.setTimestamp(Instant.now());
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        errorResponse.setXRequestId(MDC.get(MDCKey.X_REQUEST_ID));

        final var sqlException = e.getSQLException();
        String message = sqlException.getMessage().split("\n")[0];
        errorResponse.setMessage(message);

        return errorResponse;
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ApiErrorResponse handleServerException(Exception e, WebRequest request) {
        log.error(e.getMessage(), e);
        final var errorResponse = new ApiErrorResponse();
        errorResponse.setTimestamp(Instant.now());
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setMessage(Optional.ofNullable(e.getMessage()).orElse(NO_MESSAGE_AVAILABLE));
        errorResponse.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        errorResponse.setXRequestId(MDC.get(MDCKey.X_REQUEST_ID));

        if (e instanceof AbstractErrorCodeException) {
            final var code = ((AbstractErrorCodeException) e).getCode();
            errorResponse.setCode(code);
        }

        return errorResponse;
    }

}


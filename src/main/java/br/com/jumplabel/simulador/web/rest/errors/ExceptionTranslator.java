package br.com.jumplabel.simulador.web.rest.errors;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import br.com.jumplabel.simulador.service.dto.ErrorDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 */
@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(ConcurrencyFailureException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorDTO processConcurencyError(ConcurrencyFailureException ex) {
    	ErrorDTO errorDTO = new ErrorDTO();
    	errorDTO.setResult(ResultDTO.getOperacaoNaoExecutadaResultError(ErrorConstants.ERR_CONCURRENCY_FAILURE, null));
        return errorDTO;
    }
 
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }

    @ExceptionHandler(CustomParameterizedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ParameterizedErrorVM processParameterizedValidationError(CustomParameterizedException ex) {
        return ex.getErrorVM();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorDTO processAccessDeniedException(AccessDeniedException e) {
    	ErrorDTO errorDTO = new ErrorDTO();
    	errorDTO.setResult(ResultDTO.getOperacaoNaoExecutadaResultError(ErrorConstants.ERR_ACCESS_DENIED, e.getMessage()));
        return errorDTO;
    }

    private ErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
    	ResultDTO dto = ResultDTO.getValidacaoResultError(ErrorConstants.ERR_VALIDATION);

        for (FieldError fieldError : fieldErrors) {
            dto.addFieldErrors(fieldError.getObjectName(), fieldError.getField(), fieldError.getCode());
        }
    	ErrorDTO errorDTO = new ErrorDTO();
    	errorDTO.setResult(dto);
        return errorDTO;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorDTO processMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
    	ErrorDTO errorDTO = new ErrorDTO();
    	errorDTO.setResult(ResultDTO.getOperacaoNaoExecutadaResultError(ErrorConstants.ERR_METHOD_NOT_SUPPORTED, exception.getMessage()));
    	return errorDTO;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> processRuntimeException(Exception ex) {
        BodyBuilder builder;
        ErrorDTO errorDTO = new ErrorDTO();
        ResultDTO resultDTO;
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            builder = ResponseEntity.status(responseStatus.value());
            resultDTO = ResultDTO.getOperacaoNaoExecutadaResultError("error." + responseStatus.value().value(), responseStatus.reason());
        } else {
            builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            resultDTO = ResultDTO.getOperacaoNaoExecutadaResultError(ErrorConstants.ERR_INTERNAL_SERVER_ERROR, ExceptionUtils.getMessage(ex));
        }
        errorDTO.setResult(resultDTO);
        return builder.body(errorDTO);
    }
}

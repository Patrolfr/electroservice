package komo.fraczek.electronicsservice.exception;


import komo.fraczek.electronicsservice.domain.ServiceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;

@ControllerAdvice
@RestController
public class EquipmentExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentExceptionHandler.class);

    @ExceptionHandler(CodeNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public final ExceptionResponse handleCodeNotFoundException(CodeNotFoundException exception){
        return new ExceptionResponse("Service code not found.","No equipment of service code " + exception.getCode() + " found.");
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public final ExceptionResponse handleCategoryNotFoundException(CategoryNotFoundException exception){
        return new ExceptionResponse("Category not found.","Category " + exception.getCategory() + " does not exists.");
}

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleExceptionMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ValidationError.createFromErrors(exception.getBindingResult());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleExceptionMethodArgumentTypeMismatchException(RuntimeException exception) {
        return new ExceptionResponse("Invalid servce status.","Allowed values are: "  + Arrays.asList(ServiceStatus.values()) + ".");
    }
}
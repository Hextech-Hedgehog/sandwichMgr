package sandwich.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sandwich.error.ApiError;
import sandwich.exception.UserNotFoundException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /*@ExceptionHandler(value = Exception.class)
    protected ResponseEntity<? extends Object> handlePersonNotFound (Exception exception, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return handleApiErrorResponse(exception, "person not found", status);
    }*/

    @ExceptionHandler(value = UserNotFoundException.class)
    protected ResponseEntity<? extends Object> handlePersonAlreadyExists (Exception exception, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        return handleApiErrorResponse(exception, exception.getMessage(), status);
    }

    /*@ExceptionHandler(value = PersonCanNotBeDeletedException.class)
    protected ResponseEntity<? extends Object> handlePersonCanNotBeDeleted (PersonCanNotBeDeletedException exception, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        return handleApiErrorResponse(exception, "person cannot be removed", status);
    }*/

    private ResponseEntity<? extends Object> handleApiErrorResponse(Exception exception, String errorTitle, HttpStatus status) {
        ApiError err = new ApiError(errorTitle, status.value(), exception.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err,responseHeaders,status);
    }

    /*@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError err = new ApiError("invalid arguments", status.value(), ex.getMessage());
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ValidationError> validationErrorList = err.getInvalidParams();
        for (FieldError fe : fieldErrors) {
            ValidationError validationError = new ValidationError();
            validationError.setName(fe.getField());
            validationError.setReason(fe.getDefaultMessage());
            validationErrorList.add(validationError);
        }
        headers.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<Object>(err, headers, status);
    }*/

    /*@ExceptionHandler(value = PersonCanNotBeDeletedException.class)
    protected ResponseEntity<? extends Object> handlePersonCanNotBeDeleted (PersonCanNotBeDeletedException exception, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        return handleApiErrorResponse(exception, "person cannot be removed", status);
    }*/
}


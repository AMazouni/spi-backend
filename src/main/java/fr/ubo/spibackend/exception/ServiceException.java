package fr.ubo.spibackend.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {

    String errorMeassage;
    HttpStatus httpStatus;

    public ServiceException(String message,HttpStatus httpStatus) {
        this.errorMeassage=message;
        this.httpStatus=httpStatus;
    }

    public ServiceException() {
    }

    public String getErrorMeassage() {
        return errorMeassage;
    }

    public void setErrorMeassage(String errorMeassage) {
        this.errorMeassage = errorMeassage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}

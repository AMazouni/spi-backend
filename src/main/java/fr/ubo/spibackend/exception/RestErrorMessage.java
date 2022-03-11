package fr.ubo.spibackend.exception;

public class RestErrorMessage {

    String errorMeassage;

    public RestErrorMessage(String message) {
        this.errorMeassage=message;
    }

    public RestErrorMessage() {
    }

    public String getErrorMeassage() {
        return errorMeassage;
    }

    public void setErrorMeassage(String errorMeassage) {
        this.errorMeassage = errorMeassage;
    }
}

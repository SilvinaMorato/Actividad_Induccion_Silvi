package model;

public class ErrorResponse {

    private final String message;
    private final Integer error;



    public ErrorResponse(Integer error, String message) {
        this.error = error;
        this.message = message;


    }


    public String getMessage() {
        return message;
    }

    public Integer getError() {
        return error;
    }

}
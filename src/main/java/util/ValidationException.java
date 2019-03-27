package util;

public class ValidationException extends Exception {

    private Object object;
    private Integer statusCode;

    public ValidationException(Object object, Integer statusCode) {
        this.object = object;
        this.statusCode = statusCode;
    }

    public ValidationException(String message, Object object, Integer statusCode) {
        super(message);
        this.object = object;
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "ValidationException{" +
                "object=" + object +
                ", statusCode=" + statusCode +
                '}';

    }

    public Object getObject() {
        return object;
    }

    public Integer getStatusCode() {
        return statusCode;
    }


}

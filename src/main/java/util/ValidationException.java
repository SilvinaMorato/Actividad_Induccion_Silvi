package util;

import java.util.List;

public class ValidationException extends Exception {

    private List<String> listItem;
    private Integer statusCode;

    public ValidationException(List<String> listItem, Integer statusCode) {
        this.listItem = listItem;
        this.statusCode = statusCode;
    }

    public ValidationException(String message, List<String> listItem, Integer statusCode) {
        super(message);
        this.listItem = listItem;
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "ValidationException{" +
                "listItem=" + listItem +
                ", statusCode=" + statusCode +
                '}';
    }

    public Object getObject() {
        return listItem;
    }

    public Integer getStatusCode() {
        return statusCode;
    }


}

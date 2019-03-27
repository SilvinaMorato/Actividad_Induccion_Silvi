package util;

import spark.Request;

public class RequestUtil {

    public static <T> T getBodyParameter(Request request, String parameter) {
        try {
            return (T) Json.INSTANCE.requestToMap(request).get(parameter);
        } catch(Exception e) {
            return null;
        }
    }


}

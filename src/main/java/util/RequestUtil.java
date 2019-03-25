package util;
import Model.ErrorResponse;
import com.mercadopago.exceptions.MPException;
import  spark.Request;
import spark.Response;

public class RequestUtil {

    public static <T> T getBodyParameter(Request request, String parameter) {
        try {
            return (T) Json.INSTANCE.requestToMap(request).get(parameter);
        } catch (Exception e) {
            return null;
        }
    }


}

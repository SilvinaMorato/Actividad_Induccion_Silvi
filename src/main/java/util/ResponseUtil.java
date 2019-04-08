package util;

import dto.PreferenceDTO;
import model.ErrorResponse;
import com.mercadopago.exceptions.MPException;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

import javax.validation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ResponseUtil {

    private static String responseBody;
    private static Set<ConstraintViolation<PreferenceDTO>> val;
    private static   Map<String, Object> mapJSON = new HashMap<>();
    private static  ErrorResponse errorResponse;

    /**
     *
     * @param exception
     * @param req
     * @param res
     */
    public static void excepcionInterruption(Exception exception, Request req, Response res) {

        String message = exception.getMessage().isEmpty() ? exception.toString() : exception.getMessage();
        errorResponse = new ErrorResponse(HttpStatus.SC_NOT_FOUND, "NO se pudo guardar la preferencia. Datos no encontrados.");
        mapJSON.put("Estado", errorResponse.getError());
        mapJSON.put("Mensaje", errorResponse.getMessage());
        mapJSON.put("Datos erroneos", exception.toString());
        //consultar bien el numero
        res.status(400);
        responseBody = (Json.INSTANCE.toJsonStringObj(mapJSON));
        res.type("application/json");
        res.body(responseBody);
    }

    /**
     *
     * @param exception
     * @param req
     * @param res
     */
   public static void mpExceptionInterruption(MPException exception, Request req, Response res) {

        errorResponse = new ErrorResponse(HttpStatus.SC_BAD_REQUEST, "Error en el sistema, no se pudo realizar la carga de la preferencia");
        mapJSON.put("Estado", errorResponse.getError());
        mapJSON.put("Mensaje", errorResponse.getMessage());
        mapJSON.put("Datos errone0s", exception.toString());
        responseBody = (Json.INSTANCE.toJsonStringObj(mapJSON));
        res.status(400);
        res.type("application/json");
        res.body(responseBody);
    }

    /**
     *
     * @param exception
     * @param req
     * @param res
     */
    public static void valException(ValidationException exception, Request req, Response res) {

        //crear un mapa
        mapJSON.put("estado", exception.getStatusCode());
        mapJSON.put("mensaje", exception.getMessage());
        mapJSON.put("datos_erroneos", exception.getObject());
        responseBody = (Json.INSTANCE.toJsonStringObj(mapJSON));
        res.status(exception.getStatusCode());
        res.type("application/json");
        res.body(responseBody);
    }


}

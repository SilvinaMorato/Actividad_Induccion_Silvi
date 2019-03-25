package util;

import Model.ErrorResponse;
import com.mercadopago.exceptions.MPException;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;

public class ResponseUtil {

    private final static String error = "Parametro no valido";

    public static void excepcionInterruption(Exception exception, Request req, Response res) {


        String message = exception.getMessage ().isEmpty () ? exception.toString () : exception.getMessage ();
        int a = message.indexOf( "Unexpected" );
        ErrorResponse errorResponse;
        if (a != -1) {
            errorResponse = new ErrorResponse ( SC_NOT_FOUND, "Datos invalidos o no encontrados " );
            res.status(404);
        }
        else {
            errorResponse = new ErrorResponse ( SC_NOT_FOUND, "Parámetro incorrectamente ingresado" );
            res.status(400);
        }

        String responseBody = (Json.INSTANCE.toJsonString(errorResponse));
        res.type("application/json");
        res.body(responseBody);

    }

    public static void mpExceptionInterruption(MPException exception, Request req, Response res){
        String message = exception.getMessage().isEmpty() ? exception.toString() : exception.getMessage();
        ErrorResponse errorResponse = new ErrorResponse ( HttpStatus.SC_INTERNAL_SERVER_ERROR, "Error interno del sistema" );
        String responseBody = (Json.INSTANCE.toJsonString(errorResponse));

        res.status(500);
        res.type("application/json");
        res.body(responseBody);

    }



}

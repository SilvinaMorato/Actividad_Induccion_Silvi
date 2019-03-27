package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import controller.ControllerMethods;
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


    public static void excepcionInterruption(Exception exception, Request req, Response res) {


        String message = exception.getMessage().isEmpty() ? exception.toString() : exception.getMessage();
        ErrorResponse errorResponse;
        errorResponse = new ErrorResponse(HttpStatus.SC_NOT_FOUND, "NO se recibio ningun dato.");
        res.status(404);
        responseBody = (Json.INSTANCE.toJsonString(errorResponse));
        res.type("application/json");
        res.body(responseBody);

    }


    public static void mpExceptionInterruption(MPException exception, Request req, Response res) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Error interno del sistema");
        responseBody = (Json.INSTANCE.toJsonString(errorResponse));
        res.status(500);
        res.type("application/json");
        res.body(responseBody);

    }

    public static void valException(ValidationException exception, Request req, Response res) {

        String message = exception.getMessage() == null || exception.getMessage().isEmpty() ? exception.toString() : exception.getMessage();
        //crear un mapa
        Map<String, Object> mapJSON = new HashMap<>();

        mapJSON.put("Estado", exception.getStatusCode());
        mapJSON.put("Mensaje", exception.getMessage());
        mapJSON.put("Datos errones", exception.getObject());
        responseBody = (Json.INSTANCE.toJsonStringObj(mapJSON));
        res.status(exception.getStatusCode());
        res.type("application/json");
        res.body(responseBody);

    /*  //  ErrorResponse errorResponse;
    //    errorResponse = new ErrorResponse (HttpStatus.SC_BAD_REQUEST,message );
        res.status(400);
        responseBody = (Json.INSTANCE.toJsonString());
        res.type("application/json");
        res.body(responseBody)*/
        ;

    }


    //Metodo que valida se las anotaciones pasaron correctamente
    public static Object validateParamDinamic(PreferenceDTO pref) {


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<PreferenceDTO>> validations = validator.validate(pref);
        for(ConstraintViolation violation : validations) {
            System.out.println(violation.getPropertyPath());
            System.out.println(violation.getMessage());

        }
        return validations;

    }


}

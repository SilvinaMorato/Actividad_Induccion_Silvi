

import controller.PreferenceController;
import controller.MethodsRender;

import controller.TokenizeController;
import spark.*;
import com.mercadopago.exceptions.MPException;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import util.ConfigAccess;
import util.JsonUtils;
import util.ResponseUtil;
import util.ValidationException;


public class Main {


    public static void main(String[] args) throws MPException {

        //Metodo de configuración de acceso
        ConfigAccess.accessConfig();

        Spark.exception(MPException.class, ResponseUtil::mpExceptionInterruption);
        Spark.exception(ValidationException.class, ResponseUtil::valException);
        Spark.exception(Exception.class, ResponseUtil::excepcionInterruption);

        //creo la  preferencia
        Spark.post("/createPreference", PreferenceController::createPreference, JsonUtils.json());

        //muestro la preferencia
        Spark.get("/form-1", MethodsRender::render, new ThymeleafTemplateEngine());

        //Cargo datos y pagos
        Spark.get("/form-3", MethodsRender::renderData, new ThymeleafTemplateEngine());

        //Obtengo el pago.
        Spark.post("/form-3", TokenizeController::paymentPreference, JsonUtils.json());


    }

}





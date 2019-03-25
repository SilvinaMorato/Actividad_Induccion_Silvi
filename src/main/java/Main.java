
import Controller.ControllerMethods;
import Controller.MethodsRender;
import Model.ErrorResponse;
import com.mercadopago.*;
import org.apache.http.HttpStatus;
import spark.*;
import com.mercadopago.exceptions.MPException;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import util.ConfigAccess;
import util.Json;
import util.RequestUtil;
import util.ResponseUtil;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;


public class Main {



    public static void main(String[] args) throws MPException{

        //Methods configuration de Access
        ConfigAccess.accessConfig ();

        Spark.exception ( Exception.class, ResponseUtil::excepcionInterruption);
        Spark.exception ( MPException.class,ResponseUtil::mpExceptionInterruption );

        //Create preference
        Spark.post ( "/createPreference", ControllerMethods::createPreference, JsonUtils.json ());

        //show preference in form
        Spark.get("/form-1", MethodsRender::render, new ThymeleafTemplateEngine());


    }



}





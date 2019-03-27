
import controller.ControllerMethods;
import controller.MethodsRender;
import spark.*;
import com.mercadopago.exceptions.MPException;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import util.ConfigAccess;
import util.ResponseUtil;
import util.ValidationException;


public class Main {


    public static void main(String[] args) throws MPException {

        //Methods configuration de Access
        ConfigAccess.accessConfig();

        Spark.exception(MPException.class, ResponseUtil::mpExceptionInterruption);
        Spark.exception(ValidationException.class, ResponseUtil::valException);
        Spark.exception(Exception.class, ResponseUtil::excepcionInterruption);

        //Create preference
        Spark.post("/createPreference", ControllerMethods::createPreference, JsonUtils.json());

        //show preference in form
        Spark.get("/form-1", MethodsRender::render, new ThymeleafTemplateEngine());


    }

}





package Controller;

import com.mercadopago.exceptions.MPException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class MethodsRender {

    public static ModelAndView render(Request req, Response rep) throws MPException {
        Map<String, Object> params = new HashMap<> ();

        if (ControllerMethods.preference != null) {
            params.put ( "name", ControllerMethods.preference.getPayer ().getName () );
            params.put ( "surname", ControllerMethods.preference.getPayer ().getSurname () );
            params.put ( "DNI", ControllerMethods.preference.getPayer ().getIdentification ().getNumber () );
            params.put ( "precio", ControllerMethods.preference.getItems ().get ( 0 ).getUnitPrice () );
            params.put ( "titulo", ControllerMethods.preference.getItems ().get ( 0 ).getTitle () );
            params.put ( "correo", ControllerMethods.preference.getPayer ().getEmail () );
            params.put ( "dni", ControllerMethods.preference.getPayer ().getIdentification ().getNumber () );
            params.put ( "initPoint", ControllerMethods.preference.getInitPoint () );
            params.put ( "idPreference", ControllerMethods.preference.getId () );
        }
        return new ModelAndView ( params, "form-1" );
    }
}

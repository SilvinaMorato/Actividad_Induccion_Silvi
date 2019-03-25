package Controller;

import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.*;
import dto.PreferenceDTO;
import spark.Request;
import spark.Response;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static util.Json.INSTANCE;

public class ControllerMethods {
    //  public static String validationErrors;

    public static  Preference preference ;
    public static Set<ConstraintViolation<PreferenceDTO>> validations;

    // llamar primero a este metodo para ver si estructura general si esta OK

    public static Object createPreference( Request req, Response res) throws Exception {
        preference = new Preference ();
       /*
        //Validate body is Empty
        if (req.body ().isEmpty ()) {
            res.status ( HttpStatus.SC_NOT_FOUND );
            menssageError = "No se recibió ningun  parámetro";

            return menssageError;
        }
    //Validate is parameter is valid
        if (!Json.INSTANCE.isJSON ( req.body ())) {
            res.type("application/json");
            res.status ( HttpStatus.SC_BAD_REQUEST);
            menssageError = "No es correcto el formato de los parametros";
            return menssageError;
        }
*/

        PreferenceDTO pref = INSTANCE.mapTo ( req.body (), PreferenceDTO.class );

        validations = (Set<ConstraintViolation<PreferenceDTO>>) validateParamDinamic ( pref );
        if (!validations.isEmpty ()){
            throw new  Exception();
        }
        //Save payer and Item data
        Item item = new Item ();
        item.setTitle ( pref.getItem ().getTitulo () )
                .setUnitPrice ( Float.valueOf ( pref.getItem ().getUnitPrice () ) )
                .setQuantity ( Integer.valueOf ( (pref.getItem ().getQuantity ()) ) );
        Payer payer = new Payer ();
        payer.setEmail ( pref.getPayer ().getEmail () )
                .setName ( pref.getPayer ().getName () )
                .setSurname ( pref.getPayer ().getSurname () )
                .setIdentification ( new Identification ()
                        .setType ( pref.getPayer ().getIdentificationType () )
                        .setNumber ( pref.getPayer ().getIdentificationNumber () ) )
                .setAddress ( new Address ()
                        .setStreetNumber ( Integer.valueOf ( pref.getPayer ().getNumber () ) )
                        .setStreetName ( pref.getPayer ().getStreetAddress () ) );

        //save payer and product data in the preference
        preference.setPayer ( payer );
        preference.appendItem ( item );

        //Validar and save preference
        preference.save ();
        if (preference.getInitPoint () == null) {

            return preference.getLastApiResponse ().getJsonElementResponse ();
        }
        return preference.getInitPoint ();
    }


    private static Object validateParamDinamic(PreferenceDTO pref){

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory ();
        Validator validator = factory.getValidator ();

        Set<ConstraintViolation<PreferenceDTO>> validations = validator.validate ( pref );
        for (ConstraintViolation violation : validations) {
            System.out.println ( violation.getPropertyPath () );
            System.out.println ( violation.getMessage () );
        }
        return validations;

    }

  

}


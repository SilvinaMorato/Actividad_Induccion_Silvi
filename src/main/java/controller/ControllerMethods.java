package controller;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.*;
import dto.PreferenceDTO;
import org.apache.http.HttpStatus;
import spark.ExceptionHandler;
import spark.ExceptionMapper;
import spark.Request;
import spark.Response;
import util.Json;
import util.ResponseUtil;
import util.ValidationException;

import javax.validation.*;
import java.util.*;

import static util.Json.INSTANCE;

public class ControllerMethods {


    public static Preference preference;
    public static Set<ConstraintViolation<PreferenceDTO>> validations;

    // llamar primero a este metodo para ver si estructura general si esta OK

    public static Object createPreference(Request req, Response res) throws Exception {
        preference = new Preference ();
        PreferenceDTO pref = INSTANCE.mapTo ( req.body (), PreferenceDTO.class );

        //valida los parámetros a través de un metodo - Se obtiene un Set.
        validations = (Set<ConstraintViolation<PreferenceDTO>>) ResponseUtil.validateParamDinamic ( pref );

        //Arma una lista con el campo erroneo y el mensaje de validacion, salta Excepción
        if (!validations.isEmpty ()) {

            List<String> listV = new ArrayList<> ();
            validations.forEach ( t -> listV.add ( t.getPropertyPath () + ", " + t.getMessage () ) );
            throw new ValidationException ( "Datos invalidos", listV, 400 );
        }

//          mapVal = validations.stream ()
//                    .collect ( Collectors.toList ( ConstraintViolation::getPropertyPath+","+ ConstraintViolation::getMessage ) );
//

        //Guarda el pagador y el producto
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

        //Guardar pagador y el item(producto)
        preference.setPayer ( payer );
        preference.appendItem ( item );

        //Validar y guardar la preferencia
        preference.save ();
        if (preference.getInitPoint () == null) {

            return preference.getLastApiResponse ().getJsonElementResponse ();
        }
        Map<String, Object> map = new HashMap<> ();
        map.put ( "Estado", res.status () );
        map.put ( "InitPoint", preference.getInitPoint () );
        return map;

    }


}


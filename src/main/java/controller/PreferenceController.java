package controller;



import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import dto.PreferenceDTO;
import service.PreferenceService;
import spark.Request;
import spark.Response;
import util.ValidationException;
import javax.validation.*;
import java.util.*;
import static util.Json.INSTANCE;

public class PreferenceController {

    private final PreferenceService preferenceService;
    public PreferenceController(PreferenceService preferenceService) {
           this.preferenceService = preferenceService;
    }

    /**
     * save de PREFERENCE
     * @param req
     * @param res
     * @return Map
     *
     */

    public static Map<String,Object> createPreference(Request req, Response res) throws Exception {
        PreferenceDTO preferenceDTO = (INSTANCE.mapTo(req.body(), PreferenceDTO.class));
       return createPreferenceValidate(preferenceDTO);
    }


    public static Map<String, Object> createPreferenceValidate(PreferenceDTO preferenceDTO) throws Exception {
        ArrayList<String> validationLilst = validatePreference(preferenceDTO);
        if( validationLilst != null ) {
            throw new ValidationException("Datos invalidos", validationLilst, 400);
        }

        Preference preference = PreferenceService.getInstance().savePreference(preferenceDTO);

        if(preference.getInitPoint() == null) {
            throw new MPException("No se puedo guardar la preferencia", preference.getId(), 400);
        }
        return PreferenceService.mapPreference(preference);
    }


    /**
     * validate param for methods validateParamDinamic
     *
     * @param pref of type PreferenceDTO.
     * @return list of validation incorrect
     */
    public static ArrayList<String> validatePreference(PreferenceDTO pref) {
        ArrayList<String> validationList = null;
        Set<ConstraintViolation<PreferenceDTO>> validations = (Set<ConstraintViolation<PreferenceDTO>>) PreferenceService.validateParamDinamic(pref);

        if( !validations.isEmpty() ) {
            validationList = new ArrayList<String>();
            for(ConstraintViolation<PreferenceDTO> t : validations) {
                validationList.add(t.getPropertyPath() + ", " + t.getMessage());
            }
        }
        return validationList;
    }
}


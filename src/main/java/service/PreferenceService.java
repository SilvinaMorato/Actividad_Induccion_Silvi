package service;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Identification;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import dto.PreferenceDTO;
import model.PreferenceModel;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class PreferenceService {

    /**
     * Save the payer and the product in Preference item and payer
     *
     * @param preferenceDTO to create
     * @return Preference preference
     */
    private static final PreferenceService INSTANCE = new PreferenceService();

    private PreferenceService() {
        if (INSTANCE != null){
            throw  new IllegalStateException("Preference Service already created");
        }

    }

    public static PreferenceService getInstance(){
        return INSTANCE;
    }

    public Preference savePreference(PreferenceDTO preferenceDTO) throws MPException {
        Preference preference = new Preference();
        Item item = new Item();
        item.setTitle(preferenceDTO.getItem().getTitulo())
                .setUnitPrice(Float.valueOf(preferenceDTO.getItem().getUnitPrice()))
                .setQuantity(Integer.valueOf((preferenceDTO.getItem().getQuantity())));
        Payer payer = new Payer();
        payer.setEmail(preferenceDTO.getPayer().getEmail())
                .setName(preferenceDTO.getPayer().getName())
                .setSurname(preferenceDTO.getPayer().getSurname())
                .setIdentification(new Identification()
                        .setType(preferenceDTO.getPayer().getIdentificationType())
                        .setNumber(((preferenceDTO.getPayer().getIdentificationNumber()))));

        preference.setPayer(payer);
        preference.appendItem(item);
        preference.save();
        return preference;
    }

    /**
     * Method that validates the annotations passed correctly
     *
     * @return set<ContraintViolation < PreferenceDTO>>
     */
    public static Set<ConstraintViolation<PreferenceDTO>> validateParamDinamic(PreferenceDTO pref) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<PreferenceDTO>> validations = validator.validate(pref);
        return validations;
    }

    /**
     * Generate map
     *
     * @return map
     */

    public static Map<String, Object> mapPreference(Preference preference) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("estado", 200);
        responseMap.put("init_point", preference.getInitPoint());
        return responseMap;
    }


}

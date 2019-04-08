package controller;

import com.mercadopago.exceptions.MPException;
import dto.PreferenceDTO;
import model.PreferenceModel;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;


public class MethodsRender {

    public static ModelAndView render(Request req, Response res) throws Exception {
        Map<String, Object> params = new HashMap<>();

        //  params = (Map<String, Object>) INSTANCE.mapTo(PreferenceModel.preference, PreferenceModel.class));
        params = mapParamsShow();
        return new ModelAndView(params, "form-1");
    }

    // Antes probar mapear Preference MOdel con Jackson

    public static ModelAndView renderData(Request req, Response rep) throws MPException {
        Map<String, Object> mapD = new HashMap<>();

        return new ModelAndView(mapD, "form-3");
    }

    // method load mapping of preference save of memory.

    public static Map<String, Object> mapParamsShow() {
        Map<String, Object> params = new HashMap<>();

        if( PreferenceModel.preference != null ) {
            params.put("name", PreferenceModel.preference.getPayer().getName());
            params.put("surname", PreferenceModel.preference.getPayer().getSurname());
            params.put("DNI", PreferenceModel.preference.getPayer().getIdentification().getNumber());
            params.put("precio", PreferenceModel.preference.getItems().get(0).getUnitPrice());
            params.put("titulo", PreferenceModel.preference.getItems().get(0).getTitle());
            params.put("correo", PreferenceModel.preference.getPayer().getEmail());
            params.put("dni", PreferenceModel.preference.getPayer().getIdentification().getNumber());
            params.put("initPoint", PreferenceModel.preference.getInitPoint());
            params.put("idPreference", PreferenceModel.preference.getId());
        }
        return params;
    }

}


package util

import com.mercadopago.resources.Preference
import controller.PreferenceController
import dto.PreferenceDTO
import service.PreferenceService
import spock.lang.*

import static util.Json.INSTANCE

class pocSpockTest extends Specification {


    def "test create Preference"() {

        given:
            ConfigAccess.accessConfig()
            String preferenceJsonOK = HelperTest.getMockPreferenceOK();
            PreferenceDTO preferenceDTO = INSTANCE.mapTo(preferenceJsonOK, PreferenceDTO.class)
        when:
            Preference preference = PreferenceService.savePreference(preferenceDTO)
        then:
            preference != null
            preference.initPoint != null
    }

    def "test Methods Validation"() {

        given:
           boolean valor 
        when:

            PreferenceDTO preferenceDTO = INSTANCE.mapTo(datos, PreferenceDTO.class)
            ArrayList<String> validationList = PreferenceController.validatePreference(preferenceDTO)
              if (validationList!= null)
                 valor = false
                else  valor = true
        then :
               valor == result

        where:

            datos                          | result
        HelperTest.getMockPreferenceOK()   |    true
        HelperTest.getMockParamsInvalid()  |    false
        HelperTest.getMockParamsInvalid1() |    false
     }




     /**
     * Salta error porque no esta cargado todos los parametros.
     * @return
     */
    def " test validate PreferenceDTO"() {

        given:
        String preferenceJsonOK = HelperTest.getMockParamsInvalid()

        when:
        PreferenceDTO preferenceDTO = INSTANCE.mapTo(preferenceJsonOK, PreferenceDTO.class)

        then:
        preferenceDTO.getPayer().getEmail() == "aaaaa@aaaaa.com"
        preferenceDTO.getPayer().getName() == "Pablo"
        preferenceDTO.getPayer().getSurname() == "Lopez"
        preferenceDTO.getItem().quantity == "1"
        preferenceDTO.getItem().unitPrice == "1234"
    }


    def setup(){
        preferenceDTO=  Mock(PreferenceDTO)
        preferenceService = Mock(PreferenceService)
    }



}

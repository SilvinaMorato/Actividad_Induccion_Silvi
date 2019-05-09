package controllers;


import com.mercadopago.resources.Preference;
import controller.PreferenceController;
import dto.PreferenceDTO;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import service.PreferenceService;
import util.ConfigAccess;
import util.HelperTest;
import java.util.ArrayList;
import java.util.Map;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static util.Json.INSTANCE;


public class PreferenceControllerTest {

    private static final String preferenceJsonOK = HelperTest.getMockPreferenceOK();
    private static final String preferenceJsonInvalid = HelperTest.getMockParamsInvalid();
    private static final String base = "http://localhost:4567";
    private static final String pathPost = "/createPreference";

    /*
     *Validate that the fields are called correctly, if not, an error message should be displayed
     *     in Json format: Status, Message and Error Data.
     */

    @Test
    public void testPreferenceParams() throws Exception {

        PreferenceDTO pref = null;
        pref = INSTANCE.mapTo(HelperTest.getMockPreferenceOK(), PreferenceDTO.class);
        /*
         *  verify that the data is of the type
         */
        assertEquals("aaaaa@aaaa.com", pref.getPayer().getEmail());
        assertEquals("Pablo", pref.getPayer().getName());
        assertEquals(Integer.valueOf("1"), pref.getItem().getQuantity());
        assertEquals(Float.valueOf("123.4"), pref.getItem().getUnitPrice());
        assertEquals(Integer.valueOf("12"), pref.getItem().getIdProduct());
    }

    /**
     * Validate that the InitPoint of the preference saved in the n EXIST and verific map is not null or empty
     */
    @Test
    public void testPreferenceValidationNotNull() throws Exception {
        ConfigAccess.accessConfig();
        PreferenceDTO preferenceDTO = INSTANCE.mapTo(preferenceJsonOK, PreferenceDTO.class);
        ArrayList<String> validationList = PreferenceController.validatePreference(preferenceDTO);

        assertEquals(validationList, null);
    }

    @Test
    public void testPreferenceInitPointNotnull() throws Exception {
        ConfigAccess.accessConfig();
        PreferenceDTO preferenceDTO = INSTANCE.mapTo(preferenceJsonOK, PreferenceDTO.class);
        Preference preference = PreferenceService.getInstance().savePreference(preferenceDTO);
        Assert.assertNotNull(preference.getInitPoint());
        Map<String, Object> objectMap = PreferenceService.mapPreference(preference);
        Assert.assertNotNull(objectMap);
    }

    /**
     * validate params incorrect
     */
    @Test
    public void setPreferenceJsonInvalid() {

        String body = RestAssured
                .given()
                .baseUri(base)
                .and()
                .body(preferenceJsonInvalid)
                .when()
                .post(pathPost)
                .then()
                .log().all()
                .and().assertThat().statusCode(is(equalTo(400)))
                .and()

                .body("mensaje", equalTo("Datos invalidos"))
                .body("estado", equalTo(400))
                .body("datos_erroneos", isEmptyOrNullString())
                .and().extract().body().asString();
    }

    /**
     * validate of Json of preference save
     */
    @Test
    public void setPreferenceJsonOK() {

        String body = RestAssured
                .given().baseUri(base)
                .and()
                .body(preferenceJsonOK)
                .when()
                .post(pathPost)
                .then()
                .log().all()
                .and().assertThat().statusCode(is(equalTo(200)))
                .and()
                .body(notNullValue())
                .body(containsString("init_point"))
                .body(containsString("https://www.mercadopago.com/mla/checkout/start?pref_id"))
                .and().extract().body().asString();
    }

    /**
     * validate format messagge- lowercase and with a low script
     */
    @Test
    public void fomatMessaggeJson() {
        String body = bodyJsonDateInvalidResponse();
        String json = body.toString();
        assertTrue(json.contains("mensaje"));
        assertTrue(json.contains("estado"));
        assertTrue(json.contains("datos_erroneos"));
    }


    public static String bodyJsonDateInvalidResponse() {
        String body = RestAssured
                .given()
                .baseUri(base)
                .and()
                .body(preferenceJsonInvalid)
                .when()
                .post(pathPost)
                .then()
                .log().all()
                .and().assertThat().statusCode(is(equalTo(400)))
                .and().extract().body().asString();
        return body;
    }
}

//
//}



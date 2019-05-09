package util;


import io.restassured.RestAssured;

import javax.ws.rs.GET;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class HelperTest {

    private static final String base = "http://localhost:4567";
    private static final String pathPost = "/createPreference";


    public static String getMockPreferenceOK() {

         String preference = "{\n" +
                 "\t\"payer\": \n" +
                 "\t{\t\n" +
                 "\t\t\"email\": \"aaaaa@aaaaa.com\",\n" +
                 "\t\t\"name\":\"Pablo\",\n" +
                 "\t\t\"surname\":\"prueba\",\n" +
                 "\t\t\"identification_type\":\"DNI\",\n" +
                 "\t\t\"identification_number\":33234223\n" +
                 "\t\t\n" +
                 "\t},\n" +
                 "\t\n" +
                 "\t\"item\":{\n" +
                 "\t\t\"id_product\":12,\n" +
                 "\t\t\"quantity\":1,\n" +
                 "\t\t\"unit_price\":1213.2,\n" +
                 "\t\t\"titulo\":\"prueba nueva a realizar\"\n" +
                 "\t\n" +
                 "\t}\n" +
                 "\n" +
                 "}";

         return preference;
    }

    public static String getMockParamsInvalid(){

        String preference= "{\n" +
                "\t\"payer\": \n" +
                "\t{\t\n" +
                "\t\t\"email\": \"aaaaa@aaaaa.com\",\n" +
                "\t\t\"name\":\"Pablo\",\n" +
                "\t\t\"surname\":\"Lopez\",\n" +
                "\t\t\"identification_type\":\"DNI\",\n" +
                "\t\t\"identification_number\":33234223\n" +
                "\t\t\n" +
                "\t},\n" +
                "\t\n" +
                "\t\"item\":{\n" +
                "\t\t\"id_product\":12,\n" +
                "\t\t\"quantity\":\"\",\n" +
                "\t\t\"unit_price\":\"\",\n" +
                "\t\t\"titulo\":\"prueba nueva a realizar\"\n" +
                "\t\n" +
                "\t}\n" +
                "\n" +
                "}";
        return preference;
    }

    /**
     *
     * @return
     */
    public static String getMockParamsInvalid1(){

        String preference = "{\n" +
                "\t\"payer\": \n" +
                "\t{\t\n" +
                "\t\t\"email\": \"aaaaaaaaaa.com\",\n" +
                "\t\t\"name\":\"Pablo\",\n" +
                "\t\t\"surname\":\"prueba\",\n" +
                "\t\t\"identification_type\":\"DNI\",\n" +
                "\t\t\"identification_number\":33234223\n" +
                "\t\t\n" +
                "\t},\n" +
                "\t\n" +
                "\t\"item\":{\n" +
                "\t\t\"id_product\":12,\n" +
                "\t\t\"quantity\":1,\n" +
                "\t\t\"unit_price\":1213.2,\n" +
                "\t\t\"titulo\":\"prueba nueva a realizar\"\n" +
                "\t\n" +
                "\t}\n" +
                "\n" +
                "}";

        return preference;
    }
}



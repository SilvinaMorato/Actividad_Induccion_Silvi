package util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import Model.*;

import spark.Request;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public enum Json {

    INSTANCE;

    public final ObjectMapper mapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .registerModule(new JavaTimeModule ())
            .configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public Map<String, Object> requestToMap(Request r) throws Exception {
        String body = r.body ();
        if (body.length () == 0) {


            return new TreeMap<> ();
        }
        return mapper.readValue ( body, new TypeReference<Map<String, Object>> () {
        } );

    }

    /**
     * Method to deserialize String content to Object.
     */
    public <T> T mapTo(String str, Class<T> tClass) throws Exception {
        return mapper.readValue(str, tClass);
    }

    public String toJsonString(ErrorResponse errorResponse) {
        try {
            return mapper.writeValueAsString(errorResponse);
        } catch (IOException e) {

            // Handle parsing manually.
            return "{ " +
                    "\"error\":\" " + errorResponse.getError() + " \", " +
                    "\"message\":\" " + errorResponse.getMessage() + " \" " +
                    "}";
        }

    }


    public static boolean isJSON(String json) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(json);

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
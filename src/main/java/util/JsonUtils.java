package util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonUtils {

    public static String toJson(Object o) {
        return new Gson ().toJson(o);
    }
    public static ResponseTransformer json() {
        return JsonUtils::toJson;
    }



}

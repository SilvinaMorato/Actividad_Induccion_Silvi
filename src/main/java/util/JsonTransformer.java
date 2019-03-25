package util;

 import spark.ResponseTransformer;

    public class JsonTransformer implements ResponseTransformer {
        @Override
        public String render(Object model) throws Exception {
            return Json.INSTANCE.mapper.writeValueAsString(model);
        }

    }


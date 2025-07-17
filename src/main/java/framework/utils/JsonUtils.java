package framework.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JsonUtils {

    public static JsonNode readJson(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(new File(path));
    }
}

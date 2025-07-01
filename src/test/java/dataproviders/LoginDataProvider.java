package dataproviders;

import com.fasterxml.jackson.databind.JsonNode;
import framework.utils.JsonUtils;
import org.testng.annotations.DataProvider;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class LoginDataProvider {

    public static class User {
        public String username;
        public String password;
    }

    private static List<User> readUsers(String key) throws Exception {
        JsonNode root = JsonUtils.readJson("src/test/resources/testdata/loginData.json");
        JsonNode array = root.get(key);

        return StreamSupport.stream(array.spliterator(), false)
                .map(node -> {
                    User user = new User();
                    user.username = node.get("username").asText();
                    user.password = node.get("password").asText();
                    return user;
                })
                .collect(Collectors.toList());
    }

    @DataProvider(name = "validLoginData")
    public static Iterator<Object[]> getValidLoginData() throws Exception {
        return readUsers("valid").stream()
                .map(user -> new Object[]{user.username, user.password})
                .iterator();
    }

    @DataProvider(name = "invalidLoginData")
    public static Iterator<Object[]> getInvalidLoginData() throws Exception {
        return readUsers("invalid").stream()
                .map(user -> new Object[]{user.username, user.password})
                .iterator();
    }
}

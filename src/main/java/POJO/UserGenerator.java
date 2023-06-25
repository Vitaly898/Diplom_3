package POJO;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public static User createRandomUser() {
        String name = RandomStringUtils.randomAlphabetic(6);
        String email = RandomStringUtils.randomAlphabetic(6) + "@mail.ru";
        String password = RandomStringUtils.randomAlphabetic(8);

        return new User(name,email,password);
    }

}

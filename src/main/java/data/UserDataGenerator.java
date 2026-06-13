package data;

import com.github.javafaker.Faker;
import user.UserModel;

import java.util.Locale;

public class UserDataGenerator {

    private static final Faker faker = new Faker(Locale.ENGLISH);

    public static UserModel GenerateUser() {
        return new UserModel(generateName(), generateEmail(), generateValidPassword());
    }

    public static String generateName() {
        return faker.name().firstName();
    }

    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateValidPassword() {
        return faker.internet().password(6, 15, true, true);
    }

}
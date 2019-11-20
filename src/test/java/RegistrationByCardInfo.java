import com.github.javafaker.Faker;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Locale;

@Data
@RequiredArgsConstructor
public class RegistrationByCardInfo {
    Faker faker = new Faker(new Locale("ru"));
    private final String name = faker.name().lastName() + " " + faker.name().firstName();
    private final String phoneNumber = faker.phoneNumber().phoneNumber();
    private final String  city = faker.address().city();
}

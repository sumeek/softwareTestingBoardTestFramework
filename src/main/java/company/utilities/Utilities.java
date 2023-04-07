package company.utilities;

import java.util.UUID;

public class Utilities {

    public String generateRandomText() {

        UUID uuid = UUID.randomUUID();

        String randomText = uuid.toString().replace("-", "").substring(0, 10);

        return randomText;
    }
}

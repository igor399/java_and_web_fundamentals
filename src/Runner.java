import by.epam.lab.services.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static by.epam.lab.services.GlobalConstants.*;

public class Runner {
    public static void main(String[] args) {

        try(FileReader fileReader = new FileReader("resources/properties/settings.properties")) {
            Properties properties = new Properties();
            properties.load(fileReader);

















        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

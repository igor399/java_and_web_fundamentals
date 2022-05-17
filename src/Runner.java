import by.epam.lab.beans.Result;
import by.epam.lab.services.ResultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

import static by.epam.lab.services.GlobalConstants.*;

public class Runner {
    public static void main(String[] args) {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            ResultHandler handler = new ResultHandler();
            reader.setContentHandler(handler);
            reader.parse(XML_PATH);
            for (Result result : handler.getResults()) {
                System.out.println(result);
            }
        } catch (SAXException e) {
            System.out.println(SAX_EXCEPTION + e);
        } catch (FileNotFoundException e) {
            System.out.println(NO_FILE_EXCEPTION);
        } catch (IOException e) {
            System.err.println(IO_EXCEPTION + e);
        }
    }
}

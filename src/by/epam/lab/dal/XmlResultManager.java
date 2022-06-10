package by.epam.lab.dal;

import by.epam.lab.beans.*;
import by.epam.lab.exceptions.*;
import by.epam.lab.services.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static by.epam.lab.services.GlobalConstants.*;

public class XmlResultManager extends ResultManager {

    public XmlResultManager(String propertiesPath, MarkRepresentation markRepresentation) throws IOException {
        super(propertiesPath, markRepresentation);
    }

    @Override
    public void importData(String filePath) throws SqlDbException {
        try (Connection cn = DriverManager.getConnection(dbUrl, properties)) {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            ResultHandler handler = new ResultHandler();
            saxParser.parse(filePath, handler);
            List<ResultWrapper> results = handler.getResults();
            for (ResultWrapper result : results) {
                insertResult(cn, result);
            }
        } catch (ParserConfigurationException | SAXException | SQLException e) {
            throw new SqlDbException(e);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

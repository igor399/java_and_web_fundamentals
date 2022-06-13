package by.epam.lab.dao;

import by.epam.lab.beans.*;
import by.epam.lab.exceptions.*;
import by.epam.lab.log.ResultManager;
import by.epam.lab.services.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class ResultImplXml implements ResultDao {
    private Iterator<Result> resultIterator;

    public ResultImplXml(String propDirectory, ResultFactory resultFactory) throws ResourceReleaseException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            ResultHandler handler = new ResultHandler();
            saxParser.parse(propDirectory, handler);
            resultIterator = handler.getResults().iterator();
        } catch (SAXException | ParserConfigurationException e) {
            throw new ResourceReadException(e.getMessage());
        } catch (IOException e) {
            throw new ResourceReleaseException(e.getMessage());
        }
    }

    @Override
    public Result nextResult() {
        return resultIterator.next();
    }

    @Override
    public boolean hasResult() {
        return resultIterator.hasNext();
    }

    @Override
    public void close() throws IOException {
        resultIterator = null;
    }
}

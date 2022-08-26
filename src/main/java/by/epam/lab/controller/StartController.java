package by.epam.lab.controller;

import by.epam.lab.controller.dao.NumberDAO;
import by.epam.lab.controller.dao.NumberFactory;
import by.epam.lab.exceptions.InitException;
import by.epam.lab.utils.ConstantsJSP;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static by.epam.lab.utils.ConstantsJSP.*;
import static by.epam.lab.utils.GlobalConstants.*;

@WebServlet(
        urlPatterns = {"/start"},
        initParams = {
                @WebInitParam(name = "min.size", value = "12"),
                @WebInitParam(name = "factory.number", value = "memory")
              //@WebInitParam(name = "factory.number", value = "csv;/resources/numbers.csv")
              //@WebInitParam(name = "factory.number", value = "db;mvcStat2;epamlab;111")
        }
)
public class StartController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig sc) throws ServletException {
        super.init(sc);
        try {
            final int MIN_SIZE = Integer.parseInt(sc.getInitParameter(ConstantsJSP.MIN_SIZE));
            NumberFactory.init(sc);
            NumberDAO numberDAO = NumberFactory.getClassFromFactory();
            List<Double> numbers = numberDAO.getNumbers().stream()
                    .filter(n -> n >= MIN_VALUE && n <= MAX_VALUE)
                    .collect(Collectors.toList());

            if (numbers.size() < MIN_SIZE) {
                throw new InitException(FEW_NUM_EXCEPTION);
            }
            getServletContext().setAttribute(NUMBERS_NAME, numbers);
            getServletContext().setAttribute(MAX_VALUE_NAME, numbers.size());
        } catch (InitException e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int statsNumber = Integer.parseInt(request.getParameter(NUMBER_NAME));
        request.setAttribute(NUMBER_NAME, statsNumber);
        request.getRequestDispatcher(START_PAGE_URL).forward(request, response);
    }
}

package by.epam.lab.controller;

import by.epam.lab.model.Operation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static by.epam.lab.utils.ConstantsJSP.*;

@WebServlet("/result")
public class ResultController extends HttpServlet {

    private static final long serialVersionUID = 2L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Operation operation = Operation.valueOf(request
                .getParameter(OPERATION_NAME).toUpperCase());

        String[] numStr = request.getParameterValues(STAT_NAME);

        double[] num = Arrays.stream(numStr).mapToDouble(Double::parseDouble)
                .toArray();

        double res = operation.getResult(num);

        request.setAttribute(OPERATION_NAME, operation.name().toLowerCase());
        request.setAttribute(STAT_NAME, num);
        request.setAttribute(RESULT_NAME, res);

        getServletContext().getRequestDispatcher(RESULT_PAGE_URL)
                .forward(request, response);
    }
}

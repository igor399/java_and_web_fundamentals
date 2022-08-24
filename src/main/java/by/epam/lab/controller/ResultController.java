package by.epam.lab.controller;

import by.epam.lab.model.Operation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static by.epam.lab.utils.ConstantsJSP.*;

@WebServlet("/result")
public class ResultController extends HttpServlet {

    private static final long serialVersionUID = 2L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Operation operation = Operation.valueOf(request
                .getParameter(OPERATION_NAME).toUpperCase());

        String[] numStr = request.getParameterValues(STAT_NAME);

        int[] intInd = Arrays.stream(numStr)
                .mapToInt(Integer::parseInt)
                .toArray();

        List<Double> numbers = (List<Double>) getServletContext()
                .getAttribute(NUMBERS_NAME);

        double[] reqNum = Arrays.stream(intInd)
                .mapToDouble(numbers::get)
                .toArray();

        double res = operation.getResult(reqNum);

        request.setAttribute(OPERATION_NAME, operation.name().toLowerCase());
        request.setAttribute(STAT_NAME, reqNum);
        request.setAttribute(RESULT_NAME, res);

        getServletContext().getRequestDispatcher(RESULT_PAGE_URL)
                .forward(request, response);
    }
}

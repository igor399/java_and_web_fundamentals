package by.epam.lab.controller;

import by.epam.lab.model.Operation;
import by.epam.lab.services.ConstantsJSP;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/result")
public class ResultController extends HttpServlet {

    private static final long serialVersionUID = 2L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        Operation operation = Operation.valueOf(request.getParameter(ConstantsJSP.OPERATION_NAME).toUpperCase());
        String[] numStr = request.getParameterValues(ConstantsJSP.STAT_NAME);
        double[] num = Arrays.stream(numStr).mapToDouble(Double::parseDouble).toArray();
        double res = operation.getResult(num);
        request.setAttribute(ConstantsJSP.OPERATION_NAME, operation.name().toLowerCase());
        request.setAttribute(ConstantsJSP.STAT_NAME, num);
        request.setAttribute(ConstantsJSP.RESULT_NAME, res);
        getServletContext().getRequestDispatcher(ConstantsJSP.RESULT_PAGE_URL).forward(request, response);
    }
}

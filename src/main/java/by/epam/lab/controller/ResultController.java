package by.epam.lab.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/result")
public class ResultController extends HttpServlet {

    private static final long serialVersionUID = 2L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {


        String refferer = request.getHeader("referer");
        if (refferer == null) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        //get strOperation and strNumbers from the corresponding request parameters;
        //convert strNumbers to double numbers;
        //convert strOperation to the operation - an item of the enum Operation;
        double result = operation.getResult(numbers);

        //set attributes for the next page;
        //forward (or redirect?) the request to the next page;
    }
}

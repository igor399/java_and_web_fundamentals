package by.epam.lab.controller;

import by.epam.lab.services.ConstantsJSP;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/start")
public class StartController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String num = request.getParameter(ConstantsJSP.NUMBER_NAME);
        request.setAttribute(ConstantsJSP.NUMBER_NAME, num);
        request.getRequestDispatcher(ConstantsJSP.START_PAGE_URL)
                .forward(request, response);
    }
}

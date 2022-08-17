package by.epam.lab.controller;

import by.epam.lab.utils.ConstantsJSP;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/start")
public class StartController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String num = request.getParameter(ConstantsJSP.NUMBER_NAME);

        request.setAttribute(ConstantsJSP.NUMBER_NAME, num);

        request.getRequestDispatcher(ConstantsJSP.START_PAGE_URL)
                .forward(request, response);
    }
}

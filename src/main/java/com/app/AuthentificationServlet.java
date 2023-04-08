package com.app;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/AuthentificationServlet")
public class AuthentificationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext cntx = getServletContext();

        PrintWriter out = response.getWriter();

        String login = request.getParameter("login");
        String password = request.getParameter("password");


        ArrayList<User> users = (ArrayList<User>) cntx.getAttribute("listusers");

        if (users != null && !users.isEmpty()) {
            User foundUser = null;
            for (User it : users) {
                if (it.getPassword().equals(password) && it.getLogin().equals(login)) {
                    foundUser = it;
                    break;
                }
            }

            if (foundUser != null) {
                request.getSession().setAttribute("connecteduser", foundUser);
                request.getRequestDispatcher("gameHome.jsp").forward(request, response);
                return;

            } else {
                out.print("Login ou mot de passe incorrects");
            }
        } else {
            out.print("Login ou mot de passe incorrects");
        }
    }
}


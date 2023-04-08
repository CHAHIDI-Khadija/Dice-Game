package com.app;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext cntx = getServletContext();

        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        if (action != null && action.equals("list")) {

            ArrayList<User> list = (ArrayList<User>) cntx.getAttribute("listusers");
            if (list != null ) {
                request.setAttribute("listUsers", list);
                request.getRequestDispatcher("listUsers.jsp").forward(request, response);
                return;

            }

        } else if (action != null && action.equals("create")) {

            String nom = request.getParameter("nom");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            User user = new User();
            user.setNom(nom);
            user.setLogin(login);
            user.setPassword(password);

            ArrayList<User> list;
            if (cntx.getAttribute("listusers") == null) {
                list = new ArrayList<>();
                cntx.setAttribute("listusers", list);
            } else {

                list = (ArrayList<User>) cntx.getAttribute("listusers");
            }

            list.add(user);
            request.getRequestDispatcher("home.jsp").forward(request, response);

        }
    }
}

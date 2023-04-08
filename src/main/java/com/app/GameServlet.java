package com.app;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();
        User user = (User) s.getAttribute("connecteduser");

        StringBuffer sb = new StringBuffer();

        if (user != null) {
            if (request.getParameter("reinit") != null) {
                s.setAttribute("nbrLancees", 0);
                s.setAttribute("old_res", null);
                s.setAttribute("score", 0);
                sb.append("Nouvelle Partie");
                s.removeAttribute("listDice");
                request.getRequestDispatcher("gameHome.jsp").forward(request, response);
                return;
            }

            ServletContext cntx = getServletContext();

            PrintWriter out = response.getWriter();

            int nbrLancees = 0;
            int diceNum = Integer.parseInt(request.getParameter("diceNumber"));

             ArrayList<Dice> listDice = new ArrayList<>();
             if(s.getAttribute("listDice")==null){
                 s.setAttribute("listDice",listDice);
             }
             listDice = (ArrayList<Dice>)s.getAttribute("listDice");

            if (s.getAttribute("nbrLancees") != null) {
                nbrLancees = (Integer) s.getAttribute("nbrLancees");

            }
            if (nbrLancees < 3) {
                Random rd = new Random();
                int resultat = 1 + rd.nextInt(6);

                Dice dice = new Dice(diceNum,resultat);
                listDice.add(dice);

                s.setAttribute("nbrLancees", nbrLancees + 1);

                sb.append("resultat=" + resultat);
            }

            if(nbrLancees==2){
                int res=0;
                if((listDice.get(0).getDiceRes()<listDice.get(1).getDiceRes())
                        &&(listDice.get(1).getDiceRes()<listDice.get(2).getDiceRes())){
                    res = listDice.get(0).getDiceRes()+listDice.get(1).getDiceRes()+listDice.get(2).getDiceRes();
                }
                else if((listDice.get(0).getDiceRes()>listDice.get(1).getDiceRes())
                        &&(listDice.get(1).getDiceRes()>listDice.get(2).getDiceRes())){
                    res = listDice.get(0).getDiceRes()*listDice.get(1).getDiceRes()*listDice.get(2).getDiceRes();
                }
                else if((listDice.get(0).getDiceNum()==listDice.get(1).getDiceNum())
                        ||(listDice.get(1).getDiceNum()==listDice.get(2).getDiceNum())){
                    res = -1;
                }
                user.setScore(res);
                user.setBestScore();
                request.getRequestDispatcher("result.jsp").forward(request, response);
            }

            request.setAttribute("msg", sb.toString());
            request.getRequestDispatcher("gameHome.jsp").forward(request, response);
            return;

        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
    }
}

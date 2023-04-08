<%@ page import="com.app.Dice" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.app.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Result</title>
</head>
<body>
<h1>Result:</h1>
<%
  // Get the dice rolls from the session
  ArrayList<Dice> listDice = (ArrayList<Dice>) session.getAttribute("listDice");
  User user = (User) session.getAttribute("connecteduser");

  for (Dice d: listDice) {
    out.println("Dice "+ d.getDiceNum()+ " : "+ d.getDiceRes());
    out.println("<br>");
  }
  out.print("votre score "+ user.getScore());
  out.println("<br>");
%>
<a href="GameServlet?reinit">Rejouer</a>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: 840
  Date: 02/04/2023
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PLAY</title>
</head>
<body>
<div>
    <a href="GameServlet?reinit"> Renitialize the game</a>

    <a href="UserServlet?action=list"> Best Score</a>
    
    <a href="DeconnectServlet"> Log Out</a>
</div>
<form action="http://localhost:8080/NewDiceGame_war_exploded/GameServlet">
    <div>
        <label for="diceNumber">choose your dice:</label>
        <select id="diceNumber" name="diceNumber">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
        </select>
        <button type="submit">Play</button>
    </div>
</form>


<%
    out.print( request.getAttribute("msg"));
%>



</body>
</html>

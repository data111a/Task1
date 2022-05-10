<%--<jsp:useBean id="name" scope="request" type="tool"/>--%>
<%--
  Created by IntelliJ IDEA.
  User: KILLGATES
  Date: 5/8/2022
  Time: 4:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<form action="show-cities" method="post">
<center>
    <h1>
        Hello ${FirstName} !
    </h1>
    <br>
    <p>
        Please enter your chosen country:<input type="text" name="country">
        <input type="submit" value="NEXT">
    </p>
</center>
<%--</form>--%>
</body>
</html>

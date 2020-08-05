<%-- 
    Document   : addmessage
    Created on : Oct 18, 2012, 3:20:55 PM
    Author     : Дикий Александр Николаевич
    Version    : 1.1
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="mBean" class="om.dykyi.beans.MessageBean" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="/guestbook/index?command=Message" method="POST" >
            <table border="0">
                <tr>
                    <td>
                        <p><b>Ваше сообщение принято:</b></p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>${mBean.message}</p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Продолжить" />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>

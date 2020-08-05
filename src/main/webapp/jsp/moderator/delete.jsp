<%-- 
    Document   : delete
    Created on : Nov 12, 2012, 3:56:49 PM
    Author     : Дикий Александр Николаевич
    Version    : 1.1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="mBean" scope="session" class="om.dykyi.beans.MessageBean" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="/guestbook/index?command=Guestbook" method="POST" >
            <table border="0">
                <tr>
                    <td>
                        <p><b>Cообщение удаленно:</b></p>
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

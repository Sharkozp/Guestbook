<%-- 
    Document   : correction
    Created on : Nov 12, 2012, 3:54:25 PM
    Author     : Дикий Александр Николаевич
    Version    : 1.1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="mBean" class="beans.MessageBean" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="/Guestbook/index?command=AddCorrection" method="POST" id="comfirmed"></form> 
        <form action="/Guestbook/index?command=Guestbook" method="POST" id="canceled"></form>
        <table border="0">
            <tr>
                <td>
                    <input type="radio" name="isForAll" value="true" checked form="comfirmed">-
                    для всех    
                    <input type="radio" name="isForAll" value="false" form="comfirmed">-
                    только для администратора.
                </td>
            </tr>
            <tr>
                <td>
                    <b>Сообщение:</b>
                </td>
            </tr>
            <tr>
                <td>
                    <textarea rows="9" name="messageText" cols="64" form="comfirmed">${mBean.message}</textarea>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Отправить" form="comfirmed" />
                    <input type="submit" value="Отменить" form="canceled"/>                    
                </td>
            </tr>
        </table>
    </body>
</html>

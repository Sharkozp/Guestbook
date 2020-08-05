<%-- 
    Document   : answer
    Created on : Nov 12, 2012, 3:56:39 PM
    Author     : Дикий Александр Николаевич
    Version    : 1.1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="mBean" class="om.dykyi.beans.MessageBean" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="/guestbook/index?command=AddAnswer" method="POST" id="comfirmed"></form>
        <form action="/guestbook/index?command=Guestbook" method="POST" id="canceled"></form>

        <table border="0">
            <tr>
                <td>
                    <b>Сообщение:</b>
                </td>
            </tr>
            <tr>
                <td>
                    <textarea rows="9" name="messageText" cols="64" form="comfirmed" readonly >${mBean.message}</textarea>
                </td>
            </tr>
            <tr>
                <td>
                    <b>Ответ:</b>
                </td>
            </tr>
            <tr>
                <td>
                    <textarea rows="9" name="answerText" cols="64" form="comfirmed"></textarea>
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

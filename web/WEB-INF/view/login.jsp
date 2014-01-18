<%-- 
    Document   : login
    Created on : Oct 15, 2012, 9:14:38 AM
    Author     : Дикий Александр Николаевич
    Version    : 1.1
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="uBean" scope="session" class="beans.UserBean" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GuestBook</title>
    </head>
    <body>       
        <form id="login" action="/Guestbook/index?command=CheckLogin" method="POST"></form>
        <table border="0">
            <tr>  
                <td>
                    <b>Для входа в систему введите:</b>
                </td>
            </tr> 
            <tr>  

                <td align="right">Учетное имя:<font color="red">*</font> </td>
                <td><input type="text" required name="login" form="login" /></td>
            </tr>
            <tr>                   
                <td align="right">Пароль:<font color="red">*</font> </td>
                <td><input type="password" required name="password" form="login" /></td>
            </tr>
            <tr><td></td>
                <td>
                    <c:if test="${uBean.error}">
                        <font color="red">Ошибка ввода. Повторите попытку.</font>
                    </c:if>
                </td>
            </tr>
            <tr>                    
                <td align="right">
                    <input type="submit" value="Продолжить" form="login" />
                </td>
                <td align="left">
                    <form action="/Guestbook/index?command=Message" method="POST">
                        <input type="submit" value="Отменить" />
                    </form>
                </td>
            </tr>               
        </table>

    </body>
</html>
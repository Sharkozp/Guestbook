<%-- 
    Document   : adminusers
    Created on : Nov 16, 2012, 8:29:50 AM
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
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
            <thead>
                <tr>
                    <th align="center">Логин</th>
                    <th align="center">Фамилия</th>                    
                    <th align="center">Имя</th>                    
                    <th align="center">Управление</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${uBean.list}"> 
                    <tr>
                        <td align="center">${user.userName}</td>
                        <td align="center">${user.lastName}</td>
                        <td align="center">${user.firstName}</td>                    
                        <td align="center">
                            <form method="POST">
                                <input type="hidden" name="userName" value="${user.userName}"/>
                                <input type="submit" value="Изменить" formaction="/Guestbook/index?command=ChangeUser"/>
                                <input type="submit" value="Удалить" formaction="/Guestbook/index?command=DeleteUser"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form method="POST">
            <input type="submit" value="Добавить" formaction="/Guestbook/index?command=AddUser"/>
            <input type="submit" value="Назад" formaction="/Guestbook/index?command=Guestbook"/>
        </form>
    </body>
</html>
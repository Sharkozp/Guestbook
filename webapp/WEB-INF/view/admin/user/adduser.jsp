<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : adduser
    Created on : Nov 15, 2012, 4:07:44 PM
    Author     : Дикий Александр Николаевич
    Version    : 1.1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST">
            <table border="0">
                <tr>
                    <td align="right">Логин:</td>
                    <td>
                        <input type="text" name="userName" />                      
                    </td>
                </tr>
                <tr>
                    <td align="right">Фамилия:</td>
                    <td>
                        <input type="text" name="lastName" />
                    </td>
                </tr>
                <tr>
                    <td align="right">Имя:</td>
                    <td>
                        <input type="text" name="firstName"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">Пароль:</td>
                    <td>
                        <input type="password" name="password" />
                    </td>
                </tr>
                <tr>
                    <td align="right"></td>
                    <td>
                        <input type="submit" name="btnAddNewUser" value="Добавить" formaction="/guestbook/index?command=AddNewUser"/>
                        <input type="submit" name="btnBackUser" value="Назад" formaction="/guestbook/index?command=AdminUsers" />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>


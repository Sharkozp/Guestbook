<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : changeuser
    Created on : Nov 15, 2012, 4:07:44 PM
    Author     : Дикий Александр Николаевич
    Version    : 1.1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="uBean" scope="session" class="om.dykyi.beans.User"/>
<jsp:useBean id="modBean" scope="session" class="om.dykyi.beans.ModeratorBean" />
<jsp:useBean id="gBean" scope="session" class="om.dykyi.beans.Guestbook" />
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
                    <td align="right">Логин: </td>
                    <td><input type="text" name="userName" value="${uBean.userName}" readonly /></td>   
                </tr>
                <tr>
                    <td align="right">Фамилия: </td>
                    <td><input type="text" name="lastName" value="${uBean.lastName}" /></td> 
                </tr>
                <tr>
                    <td align="right">Имя: </td>
                    <td><input type="text" name="firstName" value="${uBean.firstName}" /></td> 
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="Изменить" formaction="/guestbook/index?command=AddChangeUser" />
                        <input type="submit" value="Назад" formaction="/guestbook/index?command=AdminUsers" />
                    </td>
                </tr>
            </table>
        </form>
                
        Добавить новую книгу:
        <form method="POST">
            <table border="0">
                <tr>
                    <td>
                        <select name="newModerBook">
                            <c:forEach var="newBook" items="${gBean.guestbooks}">
                                <option>${newBook.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr><tr>
                    <td>
                        <input type="hidden" name="userName" value="${uBean.userName}" />
                        <input type="submit" value="Добавить" formaction="/guestbook/index?command=AddModerateBook"/>
                    </td>
                </tr>
            </table>
        </form>

        Книги: 
        <c:forEach var="book" items="${modBean.listOfModeratorsBooks}">
            <form method="POST">
                <table border="0">
                    <tr>
                        <td>${book.guestbookName}</td>
                        <td align="center">
                            <input type="hidden" name="userName" value="${uBean.userName}" />
                            <input type="hidden" name="idMod" value="${book.id}" />
                            <input type="submit" value="Удалить" formaction="/guestbook/index?command=DeleteModerateBook" />
                        </td>  
                    </tr>
                </table>
            </form>
        </c:forEach>
    </body>
</html>
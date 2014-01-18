<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : adminbooks
    Created on : Nov 16, 2012, 8:29:50 AM
    Author     : Дикий Александр Николаевич
    Version    : 1.1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="gBean" scope="session" class="beans.GuestbookBean" />
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
                    <th align="center">Имя книги</th>
                    <th align="center">Описание</th>
                    <th align="center">Порядок сортировки</th>
                    <th align="center">Управление</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="row" items="${gBean.guestbooks}">
                    <tr>
                        <td align="center">
                            ${row.name}
                        </td>
                        <td align="center">
                            ${row.description}
                        </td>
                        <td align="center">
                            ${row.displayOrder}
                        </td>
                        <td align="center">
                            <form method="POST">
                                <input type="hidden" name="namebook" value="${row.name}"/>
                                <input type="submit" value="Изменить" formaction="/Guestbook/index?command=ChangeBook"/>
                                <input type="submit" value="Удалить" formaction="/Guestbook/index?command=DeleteBook"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form method="POST">
            <input type="submit" value="Добавить" formaction="/Guestbook/index?command=AddBook"/>
            <input type="submit" value="Назад" formaction="/Guestbook/index?command=Guestbook"/>
        </form>
    </body>
</html>
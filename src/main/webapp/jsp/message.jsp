<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : message
    Created on : Nov 12, 2012, 10:00:36 AM
    Author     : Дикий Александр Николаевич
    Version    : 1.1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="gBean" class="om.dykyi.beans.GuestbookBean" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form id="message" method="POST" action="/guestbook/index?command=AddMessage"></form>
        <table border="0" >
            <tr >
                <td align="center">
                    <form id="other" method="POST"></form>       
                    <input type="submit" value="Показать записи" formaction="/guestbook/index?command=Guestbook" form="other" />
                    <input type="submit" value="Администрирование" formaction="/guestbook/index?command=Login" form="other" />
                </td>
            </tr>
            <tr>
                <td align="center">
                    <table border="0">
                        <tr>
                            <td></td>
                            <td>
                                <p><b>Написать сообщение</b></p>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <input type="radio" name="forAll" value="true" checked="" form="message">-
                                для всех    
                                <input type="radio" name="forAll" value="false" form="message">-
                                только для администратора.
                            </td>
                        </tr>
                        <tr>                   
                            <td align="right">Имя книги: </td>
                            <td><select name="guestbookName" form="message">
                                    <c:forEach var="row" items="${gBean.guestbooks}">
                                        <option value="${row.id}">${row.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td valign="top" align="right">
                                Текст сообщения:<font color="red">*</font> 
                            </td>
                            <td>
                                <textarea rows="9" required placeholder="Текст сообщения" name="message" cols="64" form="message"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td valign="top" align="right">
                                Фамилия, Имя:<font color="red">*</font>
                            </td>
                            <td>
                                <input type="text" required placeholder="Ваша фамилия, имя" name="authorName" size="60" form="message">
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <b>Если хотите, укажите свои данные:</b>
                            </td>
                        </tr>
                        <tr>
                            <td valign="top" align="right">
                                Телефон:
                            </td>
                            <td>
                                <input type="text" name="phone" size="40" form="message">
                            </td>
                        </tr>
                        <tr>
                            <td valign="top" align="right">
                                E-mail: 
                            </td>
                            <td>
                                <input type="text" name="email" size="40" form="message">
                            </td>
                        </tr>
                        <tr>
                            <td valign="top" align="right">
                                ICQ:
                            </td>
                            <td>
                                <input type="text" name="icq" size="12" form="message">
                            </td>                                
                        </tr>
                        <tr>
                            <td></td>
                            <td align="left">Поля отмеченные - <font color="red">*</font> - обязательны к заполнению!</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td align="center">
                                <input type="submit" value="Отправить" form="message" />
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </body>
</html>

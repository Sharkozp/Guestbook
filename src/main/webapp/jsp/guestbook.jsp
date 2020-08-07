<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 
    Document   : guestbook
    Created on : Oct 16, 2012, 1:25:52 PM
    Author     : Дикий Александр Николаевич
    Version    : 1.1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="gBean" scope="session" class="om.dykyi.beans.GuestbookBean" />
<jsp:useBean id="mBean" scope="session" class="om.dykyi.beans.MessageBean" />
<jsp:useBean id="login" scope="session" class="om.dykyi.beans.Login" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guestbook</title>
    </head>
    <body>
        <form method="POST">
            <table border="0">
                <tr>                   
                    <td>Имя книги: </td>
                    <td><select name="nameGuestbook">
                            <c:forEach var="row" items="${gBean.guestbooks}">
                                <option>${row.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>                    
                    <td>                 

                    </td>
                </tr>               
            </table>
            <input type="submit" value="Изменить" formaction="/guestbook/index?command=Guestbook" />
            <c:if test="${login.admin}" >
                <input type="submit" value="Добавить книгу" formaction="/guestbook/index?command=AdminBooks" />
                <input type="submit" value="Добавить юзера" formaction="/guestbook/index?command=AdminUsers" />
            </c:if>
            <input type="submit" name="btnProceed" value="Продолжить" formaction="/guestbook/index?command=Message" />
        </form>        
        Найдено записей: <b>${mBean.count}</b>
        <table border="0">
            <c:forEach var="messages" items="${mBean.listMessages}">
                <tr>
                    <td>${messages.msgID}</td>
                    <td><font color="#0000FF"><b><fmt:formatDate value="${messages.creationTime}" pattern="dd.MM.yyyy HH:mm:ss" type="both" dateStyle="full" timeStyle="full" /></b>; ${messages.creatorName};</font>   
                        <c:if test="${not messages.isForAll}">
                            <font color="#FF0000">Сообщение для модератора</font>
                        </c:if>
                    </td>
                </tr>

                <c:if test="${not login.moderator and messages.isForAll}" >
                    <tr>
                        <td></td>
                        <td>${messages.messageText}</td>
                    </tr>
                    <c:if test="${not empty  messages.answerText}">
                        <tr>
                            <td></td>
                            <td><font color="#008080"><b>Ответ: </b><fmt:formatDate value="${messages.answerTime}" pattern="dd.MM.yyyy HH:mm:ss" type="both" dateStyle="full" timeStyle="full" />; ${messages.answerName}</font></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><font color="#008080">${messages.answerText}</font></td>
                        </tr>     
                    </c:if>
                </c:if>

                <c:if test="${login.moderator}" >
                    <tr>
                        <td></td>
                        <td>${messages.messageText}</td>
                    </tr>
                    <c:if test="${not empty  messages.answerText}">
                        <tr>
                            <td></td>
                            <td><font color="#008080"><b>Ответ: </b><fmt:formatDate value="${messages.answerTime}" pattern="dd.MM.yyyy HH:mm:ss" type="both" dateStyle="full" timeStyle="full" />; ${messages.answerName}</font></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><font color="#008080">${messages.answerText}</font></td>
                        </tr>     
                    </c:if>
                    <c:if test="${login.moderator}" >
                        <tr>
                            <td></td>
                            <td align="right">
                                <form method="POST">
                                    <input type="hidden" name="msgID" value="${messages.msgID}" />
                                    <input type="submit" value="Ответить" formaction="/guestbook/index?command=Answer" />
                                    <input type="submit" value="Правка" formaction="/guestbook/index?command=Correction" />
                                    <input type="submit" value="Удалить" formaction="/guestbook/index?command=Delete" />
                                </form>
                            </td>
                        </tr>  
                    </c:if>
                </c:if>
            </c:forEach>
        </table>
    </body>
</html>

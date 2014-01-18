<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : changebook
    Created on : Nov 15, 2012, 4:07:44 PM
    Author     : Дикий Александр Николаевич
    Version    : 1.1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="gBean" scope="session" class="beans.GuestbookBean"/>

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
                    <td align="right">
                        Имя книги:
                    </td>
                    <td>
                        <input type="text" name="newNamebook" value="${gBean.nameGuestbook}" readonly />                     
                    </td>                
                </tr>
                <tr>
                    <td align="right">
                        Описание книги:
                    </td>
                    <td>
                        <input type="text" name="newDescription" value="${gBean.description}"/>
                    </td>                
                </tr>
                <tr>
                    <td align="right">
                        Порядок отображения:
                    </td>
                    <td>
                        <input type="text" name="newDisplayOrder" value="${gBean.displayOrder}" />
                    </td>                
                </tr>
                <tr>
                    <td align="right">
                        <input type="submit" value="Изменить" formaction="/Guestbook/index?command=AddChangeBook" />
                    </td>
                    <td align="left">                        
                        <input type="submit" value="Назад" formaction="/Guestbook/index?command=AdminBooks" />
                    </td>
                </tr>            
            </table>
        </form>
    </body>
</html>

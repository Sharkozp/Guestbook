<%-- 
    Document   : addbook
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
                    <td align="right">
                        Имя книги:
                    </td>
                    <td>
                        <input type="text" name="newNamebook" />
                    </td>                
                </tr>
                <tr>
                    <td align="right">
                        Описание книги:
                    </td>
                    <td>
                        <input type="text" name="newDescription" />
                    </td>                
                </tr>
                <tr>
                    <td align="right">
                        Порядок отображения:
                    </td>
                    <td>
                        <input type="text" name="newDisplayOrder" />
                    </td>                
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="Добавить" formaction="/guestbook/index?command=AddNewBook" />
                        <input type="submit" value="Назад" formaction="/guestbook/index?command=AdminBooks" />
                    </td>
                </tr>            
            </table>
        </form>
    </body>
</html>

<jsp:include page="includes/header.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="error" type="java.lang.Boolean" scope="session"/>
<div class="container">
    <div class="row">
        <form action="/guestbook/index?command=CheckLogin" method="POST">
            <div class="form-group row">
                <label for="login">Login:</label>
                <input type="text" name="login" class="form-control" id="login" required>
            </div>
            <div class="form-group row">
                <label for="password">Password</label>
                <input type="password" name="password" class="form-control" id="password" required>
            </div>
            <c:if test="${error}">
                <p class="text-danger">Wrong credentials</p>
            </c:if>
            <input type="submit" value="Login" class="btn btn-primary"/>
            <a href="/guestbook/index?command=Message" class="btn btn-primary" role="button" aria-pressed="true">Cancel</a>
        </form>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>
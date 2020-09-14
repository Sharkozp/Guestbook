<jsp:include page="../includes/header.jsp"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="messageText" scope="session" type="java.lang.String"/>
<jsp:useBean id="message" class="om.dykyi.beans.Message" scope="session"/>
<div class="container">
    <div class="row">
        <form action="/guestbook/index?command=AddEdit" method="POST" id="comfirmed">
            <input type="hidden" name="messageId" value="${message.id}"/>
            <div class="form-group">
                <div class="custom-control custom-radio">
                    <input type="radio" class="custom-control-input" id="message-for-all" name="forAll" value="true"
                           <c:if test="${message.forAll}">selected</c:if>>
                    <label class="custom-control-label" for="message-for-all">For all</label>
                </div>
                <div class="custom-control custom-radio">
                    <input type="radio" class="custom-control-input" id="message-for-admin" name="forAll" value="false"
                           <c:if test="${not message.forAll}">selected</c:if> >
                    <label class="custom-control-label" for="message-for-admin">Only for administrators.</label>
                </div>
            </div>
            <div class="form-group row">
                <label for="message-text">Message text:</label>
                <textarea rows="9" id="message-text" name="messageText" cols="64"
                          form="comfirmed">${message.message}</textarea>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
            <a href="/guestbook/index?command=Message" class="btn btn-primary" role="button"
               aria-pressed="true">Cancel</a>
        </form>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>

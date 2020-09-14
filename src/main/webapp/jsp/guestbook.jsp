<jsp:include page="includes/header.jsp"/>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="guestbookList" type="java.util.List<om.dykyi.beans.Guestbook>" scope="session"/>
<jsp:useBean id="messageList" type="java.util.List<om.dykyi.beans.Message>" scope="session"/>
<jsp:useBean id="guestbookId" scope="session" type="java.lang.Integer" />
<jsp:useBean id="moderator" scope="session" type="java.lang.Boolean" />
<jsp:useBean id="messageCount" scope="session" type="java.lang.Integer" />

<div class="container">
    <div class="row">
        <form method="POST">
            <div class="form-group row">
                <label for="guestbook">Guestbook name:</label>
                <select class="form-control" name="guestbookId" id="guestbook">
                    <c:forEach var="guestbook" items="${guestbookList}">
                        <option value="${guestbook.id}" <c:if test="${guestbook.id == guestbookId}">selected</c:if>>${guestbook.name}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" value="Change" class="btn btn-primary"
                   formaction="/guestbook/index?command=Guestbook"/>
            <input type="submit" value="Continue" class="btn btn-primary"
                   formaction="/guestbook/index?command=Message"/>
        </form>
    </div>
    <div class="row">
        <h5>Records found: ${messageCount}</h5>
    </div>
    <div class="row">
        <ul class="list-group">
            <c:forEach var="message" items="${messageList}">
                <c:if test="${message.forAll}">
                    <li class="list-group-item">
                        <p>${message.id} : ${message.message}</p>
                        <p class="text-secondary"><fmt:formatDate value="${message.timeCreation}"
                                                                  pattern="dd.MM.yyyy HH:mm:ss" type="both" dateStyle="full"
                                                                  timeStyle="full"/>; ${message.authorName}</p>
                        <c:if test="${not message.forAll}">
                            <p class="text-danger">Message for admin</p>
                        </c:if>
                        <c:if test="${not empty message.answer.answerText}">
                            <p class="text-success">Answer: <fmt:formatDate value="${message.answer.answerTime}"
                                                                            pattern="dd.MM.yyyy HH:mm:ss" type="both"
                                                                            dateStyle="full" timeStyle="full" />; ${message.answer.moderatorName}</p>

                            <p class="text-success">${message.answer.answerText}</p>
                        </c:if>
                        <c:if test="${moderator and empty message.answer.answerText}">
                            <form method="POST">
                                <input type="hidden" name="messageId" value="${message.id}"/>
                                <input type="submit" value="Answer" class="btn btn-primary"
                                       formaction="/guestbook/index?command=Answer"/>
                                <input type="submit" value="Edit" class="btn btn-primary"
                                       formaction="/guestbook/index?command=Edit"/>
                                <input type="submit" value="Delete" class="btn btn-primary"
                                       formaction="/guestbook/index?command=Delete"/>
                            </form>
                        </c:if>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>

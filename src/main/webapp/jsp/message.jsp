<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/header.jsp"/>
<jsp:useBean id="guestbookList" type="java.util.List<om.dykyi.beans.Guestbook>" scope="session"/>

<div class="container">
    <div class="row">
        <form id="message" method="POST" action="/guestbook/index?command=AddMessage">
            <h3>Write message</h3>
            <div class="form-group">
                <div class="custom-control custom-radio">
                    <input type="radio" class="custom-control-input" id="message-for-all" name="forAll" value="true" checked>
                    <label class="custom-control-label" for="message-for-all">For all</label>
                </div>
                <div class="custom-control custom-radio">
                    <input type="radio" class="custom-control-input" id="message-for-admin" name="forAll" value="false">
                    <label class="custom-control-label" for="message-for-admin">Only for administrators.</label>
                </div>
            </div>

            <div class="form-group row">
                <label for="guestbook">Guestbook name:</label>
                <select class="form-control" name="guestbookId" id="guestbook">
                    <c:forEach var="guestbook" items="${guestbookList}">
                        <option value="${guestbook.id}">${guestbook.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group row">
                <label for="message-text">Message text:</label>
                <textarea class="form-control rounded-0" id="message-text" name="message" rows="10" required></textarea>
            </div>

            <div class="form-group row">
                <label for="author-name">Name:</label>
                <input type="text" class="form-control" id="author-name" name="authorName" placeholder="Enter name"
                       required>
            </div>

            <div class="form-group row"><h5>You can provide extra info about yourself</h5></div>

            <div class="form-group row">
                <label for="phone">Phone:</label>
                <input type="text" class="form-control" id="phone" name="phone" placeholder="Enter phone">
            </div>

            <div class="form-group row">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>

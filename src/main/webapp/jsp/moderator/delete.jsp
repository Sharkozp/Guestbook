<jsp:include page="../includes/header.jsp"/>
<jsp:useBean id="messageText" scope="session" type="java.lang.String" />
<div class="container">
    <div class="row">
        <form action="/guestbook/index?command=Guestbook" method="POST">
            <h3>Message was removed:</h3>

            <div class="form-group">
                <h5>${messageText}</h5>
            </div>

            <button type="submit" class="btn btn-primary">Continue</button>
        </form>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>

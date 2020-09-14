<jsp:useBean id="newMessage" class="om.dykyi.beans.Message" scope="session"/>
<jsp:include page="includes/header.jsp"/>
<div class="container">
    <div class="row">
        <form action="/guestbook/index?command=Message" method="POST">
            <h3>Thank you for your message:</h3>

            <div class="form-group">
                <h5>${newMessage.message}</h5>
            </div>

            <button type="submit" class="btn btn-primary">Continue</button>
        </form>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>

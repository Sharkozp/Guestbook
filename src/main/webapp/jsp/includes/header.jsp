<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GuestBook</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</head>
<body>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="session" class="om.dykyi.beans.User"/>
<div class="container">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="/guestbook/index?command=Guestbook">Show messages</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/guestbook/index?command=Login">Administration</a>
            </li>
            <c:if test="${user.admin}">
            <li class="nav-item">
                <a class="nav-link" href="/guestbook/index?command=AdminBooks">Admin books</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/guestbook/index?command=AdminUsers">Admin users</a>
            </li>
            </c:if>
        </ul>
    </div>
</div>

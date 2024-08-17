<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
</head>
<body>
<header class="header--main-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li><a href="#" class="btn btn--small btn--without-border">Log in</a></li>
            <li><a href="#" class="btn btn--small btn--highlighted">Sign in</a></li>
        </ul>
        <ul>
            <li><a href="${pageContext.request.contextPath}/home" class="btn btn--without-border active">Start</a></li>
            <li><a href="#steps" class="btn btn--without-border">Why?</a></li>
            <li><a href="#about-us" class="btn btn--without-border">About us</a></li>
            <li><a href="#help" class="btn btn--without-border">Foundations</a></li>
            <c:if test="${uiContext eq 'Home'}"><li><a href="${pageContext.request.contextPath}/donation" class="btn btn--without-border">Donate</a></li></c:if>
            <li><a href="#contact" class="btn btn--without-border">Contact</a></li>
        </ul>
    </nav>
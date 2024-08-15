<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="./header.jsp" %>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Start helping!<br/>
                Give away things into trusted hands
            </h1>
        </div>
    </div>
</header>

<section class="stats">
    <div class="container container--85">
        <div class="stats--item">
            <em>13</em>

            <h3>Bags given</h3>
            <p>To trusted people in need!</p>
        </div>

        <div class="stats--item">
            <em>5</em>
            <h3>Items given</h3>
            <p>That were re-used by those in need!</p>
        </div>

    </div>
</section>

<section class="steps">
    <h2>4 steps are sufficient to change lives!</h2>

    <div class="steps--container">
        <div class="steps--item">
            <span class="icon icon--hands"></span>
            <h3>Choose your unused items</h3>
            <p>clothing, toys, appliances, other</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--arrow"></span>
            <h3>Pack your items</h3>
            <p>into garbage bags</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--glasses"></span>
            <h3>Decide who you want to help</h3>
            <p>choose your trusted institution</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--courier"></span>
            <h3>Order a courier</h3>
            <p>he will arrive at the appointment time</p>
        </div>
    </div>

    <a href="#" class="btn btn--large">Register</a>
</section>

<section class="about-us">
    <div class="about-us--text">
        <h2>About us</h2>
        <p>Helping is our passion and we believe that together we can make our world a bit better!</p>
        <img src="<c:url value="/images/signature.svg"/>" class="about-us--text-signature" alt="Signature"/>
    </div>
    <div class="about-us--image"><img src="<c:url value="/images/about-us.jpg"/>" alt="People in circle"/>
    </div>
</section>

<section class="help">
    <h2>Who we support?</h2>

    <!-- SLIDE 1 -->
    <div class="help--slides active" data-id="1">
        <p>In our database you will find a list of verified Foundations with which we cooperate.
            You can check what they do.</p>

        <ul class="help--slides-items">
            <li>
                <div class="col">
                    <div class="title">I care for my health" Foundation</div>
                    <div class="subtitle">Goal and mission: Helping children from poor families.</div>
                </div>

                <div class="col">
                    <div class="title">"And whom?" Foundation</div>
                    <div class="subtitle">Goal and mission: Helping children wake up from comas.</div>
                </div>
            </li>
            <li>
                <div class="col">
                    <div class="title">"For Children" Foundation</div>
                    <div class="subtitle">Goal and mission: Helping people in difficult life situations.</div>
                </div>
                <div class="col">
                    <div class="title">“Without Home” Foundation</div>
                    <div class="subtitle">Goal and mission: Help for people without a place of residence</div>
                </div>
            </li>
        </ul>
    </div>
</section>
<%@ include file="./footer.jsp" %>
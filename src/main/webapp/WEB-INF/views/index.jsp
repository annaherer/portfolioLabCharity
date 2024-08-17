<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header.jsp" %>

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
            <em>${all_bags}</em>

            <h3>Bags given</h3>
            <p>To trusted people in need!</p>
        </div>

        <div class="stats--item">
            <em>${all_donations}</em>
            <h3>Donations</h3>
            <p>Which helped those in need!</p>
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

        <c:if test="${sampleInstitutions.size() > 0}">
            <ul class="help--slides-items">
                <li>
                    <div class="col">
                        <div class="title">${sampleInstitutions[0].name}</div>
                        <div class="subtitle">${sampleInstitutions[0].description}</div>
                    </div>
                    <c:if test="${sampleInstitutions.size() > 1}">
                        <div class="col">
                            <div class="title">${sampleInstitutions[1].name}</div>
                            <div class="subtitle">${sampleInstitutions[1].description}</div>
                        </div>
                    </c:if>
                </li>
                <c:if test="${sampleInstitutions.size() > 3}">
                    <li>
                        <div class="col">
                            <div class="title">${sampleInstitutions[3].name}</div>
                            <div class="subtitle">${sampleInstitutions[3].description}</div>
                        </div>
                        <div class="col">
                            <div class="title">${sampleInstitutions[3].name}</div>
                            <div class="subtitle">${sampleInstitutions[3].description}</div>
                        </div>
                    </li>
                </c:if>
            </ul>
        </c:if>
    </div>
</section>
<%@ include file="./footer.jsp" %>
<footer>
    <div id="contact" class="contact">
        <h2>Get in touch!</h2>
        <h3>Contact form</h3>
        <form:form method="post" action="/submitMessage" modelAttribute="userMessage">

            <div class="form-group form-group--50"><form:input type="text" path="name" placeholder="First name"/>
                <form:errors path="name"/></div>
            <div class="form-group form-group--50"><form:input type="text" path="surname" placeholder="Last name"/>
                <form:errors path="surname"/></div>
            <div class="form-group"><form:textarea rows="4" cols="50" path="messageText" placeholder="Message"/>
                <form:errors path="messageText"/></div>

            <input class="btn" type="submit" value="Submit"/>
        </form:form>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2024</span>
        <div class="bottom-line--icons">
            <a href="#" class="btn btn--small"><img src="<c:url value="/images/icon-facebook.svg"/>"></a>
            <a href="#" class="btn btn--small"><img src="images/icon-instagram.svg"/></a>
        </div>
    </div>
</footer>

<script src="js/app.js"></script>
</body>
</html>
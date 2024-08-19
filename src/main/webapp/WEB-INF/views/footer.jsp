<footer>
    <div id="contact" class="contact">
        <h2>Get in touch!</h2>
        <h3>Contact form</h3>
        <form:form method="post" action="/submitMessage" modelAttribute="userMessage">

            <div class="form-group form-group--50"><form:input type="text" path="name" placeholder="First name"/>
                <a class="error-message"><form:errors path="name"/></a></div>
            <div class="form-group form-group--50"><form:input type="text" path="surname" placeholder="Last name"/>
                <a class="error-message"><form:errors path="surname"/></a></div>
            <div class="form-group"><form:textarea rows="4" cols="50" path="messageText" placeholder="Message"/>
                <br><a class="error-message"><form:errors path="messageText"/></a></div>

            <input class="btn" type="submit" value="Submit"/>
        </form:form>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2024</span>
        <div class="bottom-line--icons">
            <a href="https://www.facebook.com" class="btn btn--small"><img src="<c:url value="/images/icon-facebook.svg"/>"></a>
            <a href="https://www.instagram.com" class="btn btn--small"><img <img src="<c:url value="/images/icon-instagram.svg"/>"></a>
        </div>
    </div>
</footer>

<script src="js/app.js"></script>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header.jsp" %>

<div class="slogan container container--90">
    <div class="slogan--item">
        <h1>Give away things you no longer want<br/>
            <span class="uppercase">those in need</span>
        </h1>

        <div class="slogan--steps">
            <div class="slogan--steps-title">Just 4 simple steps:</div>
            <ul class="slogan--steps-boxes">
                <li>
                    <div><em>1</em><span>Chose things</span></div>
                </li>
                <li>
                    <div><em>2</em><span>Pack them in bags</span></div>
                </li>
                <li>
                    <div><em>3</em><span>Choose a foundation</span></div>
                </li>
                <li>
                    <div><em>4</em><span>Order a courier</span></div>
                </li>
            </ul>
        </div>
    </div>
</div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Important!</h3>
            <p data-step="1" class="active">
                Complete the details about your 'things'.
                Thanks to this we will know who is best to pass it on to.
            </p>
            <p data-step="2">
                Pack your 'things' in bags (one bag per one donation).
            </p>
            <p data-step="3">
                Choose a foundation or foundations that you want to donate to.
            </p>
            <p data-step="4">
                Provide the address and date of collection of the items.</p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Step <span>1</span>/4</div>

        <!-- STEP 1: class .active is switching steps -->
        <form:form action="/donation" method="post" modelAttribute="donation">
            <div data-step="1" class="active">
                <h3>Zaznacz, co chcesz oddać:</h3>

                <c:forEach var="cat" items="${categories}">

                    <div class="form-group form-group--checkbox">
                        <label>
                            <form:checkbox class="category-checkbox" path="categories" value="${cat}"/>
                            <span class="checkbox"></span>
                            <span class="description">${cat.name}</span>
                        </label>
                    </div>

                </c:forEach>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step">Next</button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Enter the number of 60 L bags in which you packed your items:</h3>

                <div class="form-group form-group--inline">
                    <label>
                        Number of 60 L bags:
                        <form:input path="quantity" type="number" step="1" min="1" id="form-bags"/>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Back</button>
                    <button type="button" class="btn next-step">Next</button>
                </div>
            </div>


            <!-- STEP 4 -->
            <div data-step="3">
                <h3>Select the organization you want to help:</h3>

                <c:forEach var="inst" items="${institutions}">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <form:radiobutton class="institution-radio" path="institution" value="${inst}"/>
                            <span class="checkbox radio"></span>
                            <span class="description">
                                <div class="title">${inst.name}</div>
                                <div class="subtitle">${inst.description}</div>
                            </span>
                        </label>
                    </div>
                </c:forEach>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Back</button>
                    <button type="button" class="btn next-step">Next</button>
                </div>
            </div>

            <!-- STEP 5 -->
            <div data-step="4">
                <h3>Provide the address and date of receipt of the item by the courier:</h3>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Pickup address</h4>
                        <div class="form-group form-group--inline">
                            <label> Street <form:input path="street" id="form-street"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> City <form:input path="city" id="form-city"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Zip code <form:input path="zipCode" id="form-zip"/> </label>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4>Pick up date</h4>
                        <div class="form-group form-group--inline">
                            <label> Data <form:input type="date" path="pickUpDate" id="form-date"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Hour <form:input type="time" path="pickUpTime" id="form-time"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Remarks for courier <form:textarea path="pickUpComment" rows="5"
                                                                       id="form-comment"/> </label>
                        </div>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Back</button>
                    <button type="button" class="btn next-step">Next</button>
                </div>
            </div>

            <!-- STEP 6 -->
            <div data-step="5">
                <h3>Donation summary</h3>

                <div class="summary">
                    <div class="form-section">
                        <h4>You donate:</h4>
                        <ul>
                            <li>
                                <span class="icon icon-bag"></span>
                                <span class="summary--text" id="summary-bags">Categories and number of bags to be confirmed</span>
                            </li>

                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text"><a id="summary-institutions">Institution to be confirmed</a></span>
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4>Pick up address:</h4>
                            <ul>
                                <li ${donation.street} id="summary-street">
                                <li ${donation.city} id="summary-city">
                                <li ${donation.zipCode} id="summary-zip"></li>
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4>Pick up date:</h4>
                            <ul>
                                <li ${donation.pickUpDate} id="summary-date">
                                <li ${donation.pickUpTime} id="summary-time">
                                <li ${donation.pickUpComment} id="summary-comment">
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Back</button>
                    <button type="submit" class="btn">Confirm</button>
                </div>
            </div>
        </form:form>
    </div>
</section>

<%@include file="footer.jsp" %>
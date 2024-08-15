<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="./header.jsp" %>
<sec:authentication var="username" property="name"/>
<h1>User administration panel <a class="error-message">${param.message}</a></h1>
<table>
    <tr>
        <th>Username</th>
        <th>Email</th>
        <th>Name</th>
        <th>Admin</th>
        <th>Enabled</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="userDetails" items="${allUsers}">
        <tr>
            <td>${userDetails.userAccountDTO.username}</td>
            <td>${userDetails.userAccountDTO.email}</td>
            <td>${userDetails.userAccountDTO.firstName} ${userDetails.userAccountDTO.lastName}</td>
            <td>${userDetails.userAccountDTO.admin}</td>
            <td>${userDetails.userAccountDTO.enabled}</td>
            <td>
                <c:if test="${not(userDetails.userAccountDTO.username eq username)}">
                    <a class="btn btn-secondary btn" href="${pageContext.request.contextPath}/admin/editUser/${userDetails.userAccountDTO.id}">Edit</a>
                    <a class="btn btn-secondary btn" href="${pageContext.request.contextPath}/admin/deleteUser/${userDetails.userAccountDTO.id}">Delete</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a class="btn btn-secondary btn" href="${pageContext.request.contextPath}/admin/addUser">Add user</a>
</div>
<%@ include file="./footer.jsp" %>
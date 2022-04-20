<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>

    <jsp:attribute name="header">
         Welcome to your profile
    </jsp:attribute>


    <jsp:attribute name="footer">
        Welcome to your profile
    </jsp:attribute>

    <jsp:body>
    <c:if test="${sessionScope.user != null}">
        <p>You are logged in as "${sessionScope.user.username}".</p>
        <br>

        <h1>${requestScope.errormsg}</h1>

        <p>Update account info: </p>
        <form action="updateuser" method="post">

            <label for="newUsername">New username: </label>
            <input type="text" id="newUsername" name="newUsername"/>
            <br><br>

            <label for="newPassword">New password</label>
            <input type="password" id="newPassword" name="newPassword">
            <br><br>

            <label for="confirmNewPassword">Confirm new password</label>
            <input type="password" id="confirmNewPassword" name="confirmNewPassword">
            <br><br>

            <label for="newEmail">New email</label>
            <input type="text" id="newEmail" name="newEmail">
            <br><br>

            <label for="confirmNewEmail">Confirm email</label>
            <input type="text" id="confirmNewEmail" name="confirmNewEmail">
            <br><br><br>

            <label for="oldPassword">Old password</label>
            <input type="password" id="oldPassword" name="oldPassword">


            <input type="submit"  value="Update user"/>
        </form>
    </c:if>

    <c:if test="${sessionScope.user == null}">
        <p>You are not logged in yet. You can do it here: <a
                href="login.jsp">Login</a></p>

        <h1>${requestScope.errormsg}</h1>

        <p>Or create a new user below</p>

        <form action="createuser" method="post">
            <label for="username">Username: </label>
            <input type="text" id="username" name="username"/>
            <br> <br>

            <label for="password1">Password: </label>
            <input type="password" id="password1" name="password1"/>
            <br><br>
            <label for="password2">Confirm password: </label>
            <input type="password" id="password2" name="password2"/>
            <br><br>

            <label for="email1">Email:</label>
            <input type="text" id="email1" name="email1">
            <br><br>
            <label for="email2">Confirm email:</label>
            <input type="text" id="email2" name="email2">
            <br><br>

        <!--    <label for="admin">Admin:</label>
            <input type="checkbox" id="admin" name="admin"> -->
            <input type="submit"  value="Create user"/>
        </form>
    </c:if>



</jsp:body>

</t:pagetemplate>

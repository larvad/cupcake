<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>

    <jsp:attribute name="header">
    </jsp:attribute>


    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
    <c:if test="${sessionScope.user != null}">
        <h1>${requestScope.errormsg}</h1>

        <div class="topmargin"></div>
        <form action="updateuser" method="post">
            <div class="kontoForm">

                <h1>Update acount info</h1>
                <div class="kontoForm højreSide">
                    <input type="text" id="newUsername" name="newUsername"/>
                    <input type="password" id="newPassword" name="newPassword">
                    <input type="password" id="confirmNewPassword" name="confirmNewPassword">
                    <input type="text" id="newEmail" name="newEmail">
                    <input type="text" id="confirmNewEmail" name="confirmNewEmail">
                    <input type="password" id="oldPassword" name="oldPassword">
                </div>
                <div class="kontoForm vestreSide">
                    <label for="newUsername">New username: </label>
                    <label for="newPassword">New password</label>
                    <label for="confirmNewPassword">Confirm new password</label>
                    <label for="newEmail">New email</label>
                    <label for="confirmNewEmail">Confirm email</label>
                    <label for="oldPassword">Old password</label>

                </div>

            </div>
        <div class="kontoForm2">
            <input class="btn-shop btn-shop1 btn-order" type="submit"  value="Update user"/>
        </div>
        </form>
    </c:if>

    <c:if test="${sessionScope.user == null}">
        <meta http-equiv = "refresh" content = "0; url = login.jsp" />
    </c:if>

</jsp:body>

</t:pagetemplate>

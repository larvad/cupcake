<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Admin
    </jsp:attribute>

    <jsp:attribute name="footer">
            Admin
    </jsp:attribute>

    <jsp:body>

            <label for="admintab">Choose to view:</label>
            <select name="admintab" id="admintab">
                <option value="users">Users</option>
                <option value="orders">Orders</option>
                <%--<option value="sami">Sami</option>
                <option value="blob">Blob</option>--%>
            </select>
            <br><br>

        <div id="userTab" style="display: none">

        </div>
        <div id="orderTab" style="display: none">

        </div>




    </jsp:body>
</t:pagetemplate>
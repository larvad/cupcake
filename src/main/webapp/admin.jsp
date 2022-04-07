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

        <%--<h1>The select element</h1>

        <p>The select element is used to create a drop-down list.</p>--%>

        <form action="/action_page.php">
            <label for="cars">Choose a customer:</label>
            <select name="cars" id="cars">
                <option value="bent">Bent</option>
                <option value="torben">Torven</option>
                <option value="sami">Sami</option>
                <option value="blob">Blob</option>
            </select>
            <br><br>
            <input type="submit" value="Submit">
        </form>

        <p>Click the "Submit" button and the form-data will be sent to a page on the
            server called "action_page.php".</p>

    </jsp:body>
</t:pagetemplate>
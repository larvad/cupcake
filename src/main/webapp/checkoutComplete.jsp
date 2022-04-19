<%@ page contentType="text/html; charset=UTF-8;" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <c:if test="${sessionScope.user != null}">

            <h1>Tak for din bestilling! - Dit ordre ID er #${sessionScope.orderId} </h1>
            <h2>Du sendes automatisk tilbage til forsiden</h2>
            <meta http-equiv = "refresh" content = "5; url = index.jsp"/>



        </c:if>
        <c:if test="${sessionScope.user == null}">
        <meta http-equiv = "refresh" content = "0; url = login.jsp" />
        </c:if>
    </jsp:body>

</t:pagetemplate>
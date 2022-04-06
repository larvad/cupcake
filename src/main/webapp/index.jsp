<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Welcome to the frontpage
    </jsp:attribute>

    <jsp:attribute name="footer">
        Welcome to the frontpage
    </jsp:attribute>

    <jsp:body>

        <p>Startcode for 2nd semester </p>

        <c:if test="${sessionScope.user != null}">
            <p>You are logged in with the role of "${sessionScope.user.role}".</p>
            <label for="cupcakeTop">Choose a top:</label>
            <select name="cupcakeTop" id="cupcakeTop">
                <c:forEach var ="t" items="${requestScope.topping}">
                    <option value="<c:out value="${t.top_flavor}" />" id="<c:out value="${t.id}" />">
                            <c:out value="${t.top_flavor}: ${t.top_price} kr"/>
                    </option>
                </c:forEach>
            </select>
            <p id="topTekst"></p>
            <img id="topImg" src="images/Top/top_0.png" alt="Picture of the top part of a muffin."/>
            <label for="cupcakeBot">Choose a bottom:</label>
            <select name="cupcakeBot" id="cupcakeBot">
                <c:forEach var ="b" items="${requestScope.bottom}">
                    <option>
                            <c:out value="${b.bot_flavor}: ${b.bot_price} kr"/>
                    </option>
                </c:forEach>
            </select>
            <script>
                if (document.getElementById("cupcakeTop") != null) {
                    document.getElementById("cupcakeTop").onchange = function() {
                        document.getElementById("topTekst").innerHTML = this.value;
                        let imgTop = "images/Top/top_" + this.id + ".png";
                        document.getElementById("topImg").setAttribute("src", imgTop);
                    }
                }
            </script>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <p>You are not logged in yet. You can do it here: <a
                    href="login.jsp">Login</a></p>
        </c:if>

    </jsp:body>

</t:pagetemplate>
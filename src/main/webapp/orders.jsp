<%@ page contentType="text/html; charset=UTF-8;" pageEncoding="UTF-8" %>
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

            <h1>Kvitteringer</h1>

            <c:forEach var="e" items="${requestScope.events}">

                <div class="card text-center m-2" style="width: 14rem;">
                    <div class="card-header">
                        <h4>${e.monthName} ${e.year}</h4>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">${e.weekDay} ${e.dayOfMonth}/${e.month} &nbsp;
                            <a target="_blank" href="${e.gMapLink}"><i class="bi bi-map"></i></a>

                        </h5>
                        <p class="card-text">
                            <strong>${e.eventName}</strong><br/>
                                ${e.locationName} i ${e.city}</br>
                                ${e.limit - e.count} ledige pladser</p>
                        <p>
                            <button class="btn btn-primary"
                                    name="eventid"
                                    value="${e.eventId}"
                                    formaction="bookform">Book
                            </button>
                        </p>
                    </div>
                </div>


            </c:forEach>

            }




        </c:if>

        <c:if test="${sessionScope.user == null}">
            <meta http-equiv = "refresh" content = "0; url = login.jsp" />
        </c:if>

</jsp:body>

</t:pagetemplate>
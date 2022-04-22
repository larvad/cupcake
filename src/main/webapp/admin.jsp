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

        <c:if test="${sessionScope.user.role eq 'admin'}">
            <label for="admintab">Choose to view:</label>
            <select name="admintab" id="admintab">
                <option value="users" selected>Users</option>
                <option value="orders">Orders</option>
            </select>
            <br><br>

            <div id="userTab" style="display: block">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Savings</th>
                        <th scope="col">Tilføj guldmønter</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${sessionScope.userList}">
                        <tr>
                            <td><c:out value="${user.username}"/></td>
                            <td><c:out value="${user.email}"/></td>
                            <td><c:out value="${user.balance}"/></td>
                            <td>
                                <form action="UpdateBalance" method="post">
                                    <input type="number" name="amt" min="0"/>
                                    <input type="hidden" name="userid" value="${user.id}">
                                    <input type="submit" value="Indsæt beløb">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>


            <div id="orderTab" style="display: none">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">OrderID</th>
                        <th scope="col">UserID</th>
                        <th scope="col">Total price</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>5</td>
                        <td>100,-</td>
                    </tr>
                    <tr>
                        <td>7</td>
                        <td>13</td>
                        <td>50,-</td>

                    </tr>
                    </tbody>
                </table>
            </div>
        </c:if>

        <c:if test="${sessionScope.user.role ne 'admin'}">
            <br />
            <br />
            <p>
                Hov hov, her må du ikke være. <br />
                Klik <a href="index.jsp">her</a> for at komme tilbage til butikken.
            </p>
        </c:if>
    </jsp:body>
</t:pagetemplate>
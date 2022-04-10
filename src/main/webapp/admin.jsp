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
                <option value="orders">Orders</option>
                <option value="users">Users</option>
                <%--<option value="sami">Sami</option>
                <option value="blob">Blob</option>--%>
            </select>
            <br><br>

        <div id="userTab" style="display: none">
            <table class="table table-hover">
               <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Orders</th>
                        <th scope="col">Savings</th>
                        <th scope="col">Tilføj guldmønter</th>
                    </tr>
               </thead>
                <tbody>
                    <tr>
                        <td>user1</td>
                        <td>@mail.com</td>
                        <td>5</td>
                        <td>100$</td>
                        <td><form action="/action_page.php">
                            <input type="text" id="Add">
                            <input type="submit" value="Submit">
                        </form></td>
                    </tr>
                    <tr>
                        <td>Jan</td>
                        <td>Jan@mail.com</td>
                        <td>1</td>
                        <td>$50</td>
                        <td><form action="/action_page.php">
                            <input type="text" id="fAdd" name="fAdd">
                            <input type="submit" value="Submit">
                        </form></td>
                    </tr>
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




    </jsp:body>
</t:pagetemplate>
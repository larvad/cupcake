<%@ page contentType="text/html; charset=UTF-8;" pageEncoding="UTF-8" %>
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
            <form method="post">
            <select name="cupcakeTop" id="cupcakeTop">
                <option value="" disabled selected>Choose a top</option>
                <c:forEach var ="t" items="${sessionScope.topping}">
                    <option value="<c:out value="${t.id}" />" id="<c:out value="${t.id}" />">
                            <c:out value="${t.top_flavor}: ${t.top_price} kr"/>
                    </option>
                </c:forEach>
            </select>
            <img id="topImg" src="images/Top/top_0.png" alt="Picture of the top part of a cupcake." width="100" height="83.33"/> <br />
            <select name="cupcakeBot" id="cupcakeBot">
                <option value="" disabled selected>Choose a bottom</option>
                <c:forEach var ="b" items="${sessionScope.bottom}">
                    <option value="<c:out value="${b.id}" />" id="<c:out value="${b.id}" />">
                            <c:out value="${b.bot_flavor}: ${b.bot_price} kr"/>
                    </option>
                </c:forEach>
            </select>
            <img id="botImg" src="images/Bot/bot_0.png" alt="Picture of the bottom part of a cupcake." width="100" height="83.33"/>
                <label for="quantity">Choose amount: </label>
                <input type="number" id="quantity" name="quantity" value="1" min="1" max="99">
                <button formaction="orderList">Add to</button>


                <div class="body">
                    <div class="Cart-Container">
                        <div class="Header">
                            <h3 class="Heading">Shopping Cart</h3>
                            <button formaction="orderList" class="Action">Remove all</button>
                        </div>
                        <form method="post">
                        <c:forEach var="c" items="${sessionScope.cartCupcakes}">
                        <div class="Cart-Items">
                            <div class="image-box">
                                <div class="image-box2">
                                    <img class="imageEdit" src="images/Top/top_${c.topDTO.id}.png" style="height: 40px" />
                                </div>
                                <div>
                                    <img src="images/Bot/bot_${c.botDTO.id}.png" style="height: 40px" />
                                </div>
                            </div>
                            <div class="about">
                                <h1 class="title">Muffin #${c.cupcakeID}</h1>
                                <h3 class="subtitle">Med ${c.botDTO.bot_flavor} bund og ${c.topDTO.top_flavor} top </h3>
                            </div>
                            <div class="counter">
                                <div class="count">Antal: ${c.quantity}</div>
                            </div>
                            <div class="prices">
                                <div class="amount">Pris: ${(c.botDTO.bot_price + c.topDTO.top_price)*c.quantity}DK </div>
                                <div class="remove"><u>Remove</u></div>
                            </div>
                        </div>
                        </c:forEach>
                        </form>
                    </div>

                </div>


            </form>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <p>You are not logged in yet. You can do it here: <a
                    href="login.jsp">Login</a></p>
        </c:if>

    </jsp:body>

</t:pagetemplate>
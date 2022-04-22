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
<%--            <p>You are logged in with the role of "${sessionScope.user.role}".</p>--%>

            <div class="header">
                <h1>Cupcake Designer</h1>
            </div>
            <form method="post">
                <div class="drop">
                    <div class="boxesLeft">
                        <img id="topImg" src="images/Top/top_0.png" alt="Picture of the top part of a cupcake."/>
                    </div>
                    <div class="boxesRight3"></div>
                    <div class="boxesRight2">
                        <div class="title">
                            <p>Vælg en top</p>
                        </div>
                        <select name="cupcakeTop" id="cupcakeTop"
                                class="selector btn-selector btn-selectorGradient">
                            <option value="0" disabled selected>Choose a top</option>
                            <c:forEach var="t" items="${sessionScope.topping}">
                                <option value="<c:out value="${t.id}" />" id="<c:out value="${t.id}" />">
                                    <c:out value="${t.top_flavor}: ${t.top_price} kr"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="boxesLeft">
                        <img id="botImg" src="images/Bot/bot_0.png" alt="Picture of the bottom part of a cupcake."/>
                    </div>
                    <div class="boxesRight3">
                        <div class="title">
                            <p>Tilføj til kurv</p>
                            <input type="number" id="quantity" name="quantity" value="1" min="1" max="99" style="width: 75px">
                            <button class="btn-shop1 btn-shop" formaction="orderList" id="addTo">Add to</button>
                        </div>
                    </div>
                    <div class="boxesRight2">
                        <div class="title">
                            <p>Vælg en bund</p>
                        </div>
                        <select name="cupcakeBot" id="cupcakeBot" class="btn-selector btn-selectorGradient">
                            <option value="0" disabled selected>Choose a bottom</option>
                            <c:forEach var="b" items="${sessionScope.bottom}">
                                <option value="<c:out value="${b.id}" />" id="<c:out value="${b.id}" />">
                                    <c:out value="${b.bot_flavor}: ${b.bot_price} kr"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="boxBottom boxesRight boxesLeft"></div>
            </form>
            <div style="margin-bottom: 150px"></div>
        </c:if>
        <c:if test="${sessionScope.user == null}">
            <meta http-equiv = "refresh" content = "0; url = login.jsp" />
        </c:if>

    </jsp:body>

</t:pagetemplate>




<%--                    <div class="Cart-Container">--%>
<%--                        <div class="Header">--%>
<%--                            <h3 class="Heading">Shopping Cart</h3>--%>
<%--                            <button formaction="CartRefresh" class="Action2" name="refresh" value="102" >Remove all</button>--%>
<%--                        </div>--%>
<%--                        <c:forEach var="c" items="${sessionScope.cartCupcakes}">--%>
<%--                            <div class="Cart-Items">--%>
<%--                                <div class="image-box">--%>
<%--                                    <div class="image-box2">--%>
<%--                                        <img class="imageEdit" src="images/Top/top_${c.topDTO.id}.png" style="height: 40px" />--%>
<%--                                    </div>--%>
<%--                                    <div>--%>
<%--                                        <img src="images/Bot/bot_${c.botDTO.id}.png" style="height: 40px" />--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="about">--%>
<%--                                    <h1 class="title">Muffin #${c.cupcakeID}</h1>--%>
<%--                                    <h3 class="subtitle">Med ${c.botDTO.bot_flavor} bund og ${c.topDTO.top_flavor} top </h3>--%>
<%--                                </div>--%>
<%--                                <div class="counter">--%>
<%--                                    <div class="count">Antal:--%>
<%--                                        <form method="post">--%>
<%--                                            <input type="number" id="quantityRefresh" name="quantityRefresh" value="${c.quantity}"--%>
<%--                                                   min="1" max="99">--%>
<%--                                            <button formaction="CartRefresh" class="Action" name="refresh" value="${c.cupcakeID}">Update</button>--%>
<%--                                        </form>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="prices">--%>
<%--                                    <div class="amount">Pris: ${(c.botDTO.bot_price + c.topDTO.top_price)*c.quantity}DK </div>--%>
<%--                                    <form method="post">--%>
<%--                                        <input type="hidden" name="cupcakeToRemove" value="${c.cupcakeID}">--%>
<%--                                        <button formaction="CartRefresh" name="refresh" value="101" >Remove</button>--%>
<%--                                    </form>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </c:forEach>--%>
<%--                        <div class="checkout">--%>
<%--                            <div class="total">--%>
<%--                                <div>--%>
<%--                                    <div class="Subtotal">Sub-Total</div>--%>
<%--                                    <div class="items">Items: ${sessionScope.cartCupcakes.size()}</div>--%>
<%--                                </div>--%>
<%--                                <div class="total-amount">${sessionScope.totalPrice} DK</div>--%>
<%--                            </div>--%>
<%--                            <button class="button">Checkout</button>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

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
            <div class="checkout-pageHeader">
                <h1>Checkout</h1>
            </div>
            <div class="checkout-body">
                <div class="checkout-pageHeader checkout-bodyHeader">
                    <h1>Your Cart</h1>
                </div>
                <div class="row total-header-section">
                    <div class="col-lg-6 col-sm-6 col-6">
                        <form method="post">
                            <p>
                                <button formaction="orderReturn" class="buttonClear tilbage">
                                    <- Tilbage til cupcake designer
                                </button>
                            </p>
                        </form>
<%--                        <i class="fa fa-shopping-cart" aria-hidden="true" style="color: white"></i> <span--%>
<%--                            class="badge badge-pill badge-danger">${sessionScope.cartCupcakes.size()}</span>--%>

                    </div>
                    <div class="col-lg-6 col-sm-6 col-6 total-section text-right">
                        <form method="post">
                            <p>
                                <button formaction="checkout" class="buttonClear" name="refresh" value="102">Remove all
                                </button>
                            </p>
                        </form>
                    </div>
                </div>
                <c:forEach var="c" items="${sessionScope.cartCupcakes}">
                    <div class="row cart-detail2">
                        <div class="col-lg-4 col-sm-4 col-4 cart-detail-img2">
                            <div class="cart-upper-img">
                                <img src="images/Top/top_${c.topDTO.id}.png">
                            </div>
                            <div>
                                <img src="images/Bot/bot_${c.botDTO.id}.png">
                            </div>
                        </div>
                            <%--                        cart-detail-product--%>
                        <div class="col-lg-8 col-sm-8 col-8 cart-product">
                            <div class="row1">
                                <h2>Cupcake design #${c.cupcakeID}</h2>
                                <p class="cart-subtitle">Med ${c.botDTO.bot_flavor} bund og ${c.topDTO.top_flavor}
                                    top</p>
                            </div>
                            <div class="row2 text-center">
                                <span class="price text-info">Pris: ${(c.botDTO.bot_price + c.topDTO.top_price)*c.quantity} DK</span>
                                <span class="count"><span class="count1"> Antal: <span
                                        class="count2">${c.quantity}</span></span></span>
                            </div>
                            <div class="row3 ">
                                <form method="post">
                                    <input type="number" id="quantityRefresh" name="quantityRefresh"
                                           value="${c.quantity}"
                                           min="1" max="99">
                                    <button class="btn btn-primary btn-update" formaction="checkout" name="refresh"
                                            value="${c.cupcakeID}">Update
                                    </button>
                                    <button class="btn btn-primary btn-remove" formaction="checkout" name="refresh"
                                            value="101">x
                                        <input type="hidden" name="cupcakeToRemove" value="${c.cupcakeID}">
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="row">
                    <div class="total">
                        <div class="total-sub1">
                            <div class="Subtotal Subtotal1">Pris-Total</div>
                            <div class="items">Items: ${sessionScope.cartCupcakes.size()}</div>
                        </div>
                        <div class="total-amount total-amount1">${sessionScope.totalPrice} DK</div>
                    </div>
                </div>
                <form>
                <div class="row">
                    <p class="choosepay">Vælg betalingsform</p>
                    <div class="box1">
                        <img src="${pageContext.request.contextPath}/images/cardpayment5.png" alt="cardpayment" class="img-fluid"/>
                        <img src="${pageContext.request.contextPath}/images/mobilepay5.png" alt="mobilepay" class="img-fluid">
                    </div>
                    <div class="box1">
                        <span class="input1">
                        <input type="radio" id="cardpayment" name="payment" value="payment" checked="checked">
                        <label style="color: white" for="cardpayment">Betalingskort</label>
                        </span>
                        <span class="input2">
                        <input type="radio" id="mobilepay" name="payment" value="payment">
                        <label style="color: white" for="mobilepay">Mobilepay</label>
                        </span>
                    </div>
                </div>
                </form>
                <div class="row5">
                    <form method="post">
                        <div class="btn1">
                            <button class="btn-primary" formaction="checkout"
                                    name="refresh" value="103">
                                Cancel Order
                            </button>
                            <button class="btn-primary" formaction="checkout"
                                    name="refresh" value="103">
                                Checkout
                            </button>
                        </div>
                        <div class="btn2">

                        </div>
                    </form>
                </div>
            </div>

        </c:if>
        <c:if test="${sessionScope.user == null}">
            <meta http-equiv = "refresh" content = "0; url = login.jsp" />
        </c:if>

    </jsp:body>

</t:pagetemplate>
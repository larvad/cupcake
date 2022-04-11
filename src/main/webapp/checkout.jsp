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
                    <h1>Order ID #${sessionScope.orderId}</h1>
                </div>
                <div class="row total-header-section">
                    <div class="col-lg-6 col-sm-6 col-6">
                        <i class="fa fa-shopping-cart" aria-hidden="true" style="color: white"></i> <span
                            class="badge badge-pill badge-danger">${sessionScope.cartCupcakes.size()}</span>
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
                        <div class="col-lg-8 col-sm-8 col-8 cart-detail-product">
                            <p>Cupcake design #${c.cupcakeID}</p>
                            <p class="cart-subtitle">Med ${c.botDTO.bot_flavor} bund og ${c.topDTO.top_flavor} top</p>
                            <span class="price text-info">Pris: ${(c.botDTO.bot_price + c.topDTO.top_price)*c.quantity} DK</span>
                            <span class="count"> Antal: ${c.quantity}</span>
                            <form method="post">
                                <input type="number" id="quantityRefresh" name="quantityRefresh" value="${c.quantity}"
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
                </c:forEach>
                <div class="row">
                    <div class="total">
                        <div>
                            <div class="Subtotal">Sub-Total</div>
                            <div class="items">Items: ${sessionScope.cartCupcakes.size()}</div>
                        </div>
                        <div class="total-amount">${sessionScope.totalPrice} DK</div>
                    </div>
                    <div>
                        <form method="post">
                            <button class="btn btn-primary btn-block" formaction="checkout" name="refresh" value="103">
                                Checkout
                            </button>
                        </form>
                    </div>
                </div>
            </div>



        </c:if>
        <c:if test="${sessionScope.user == null}">
            <meta http-equiv = "refresh" content = "0; url = login.jsp" />
        </c:if>

    </jsp:body>

</t:pagetemplate>
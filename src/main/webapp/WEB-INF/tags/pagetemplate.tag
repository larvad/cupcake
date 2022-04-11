<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="da">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><jsp:invoke fragment="header"/></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/shoppingCart.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/checkout.css">

    <!-- Bootstrap CSS og JS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<header class="bg-info">
    <nav class="navbar navbar-expand-lg nav-custom">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">
                <img src="${pageContext.request.contextPath}/images/olskerLogo2.jpg" width="100px;" class="img-fluid"/>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/">Page 1</a>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/">Page 2</a>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/">Page 3</a>
                    <c:if test="${sessionScope.user == null }">
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/login.jsp">Login</a>
                    </c:if>
                    <c:if test="${sessionScope.user != null }">
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/logout">Log out</a>
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12 col-sm-12 col-12 main-section">
                                <div class="dropdown">
                                    <button type="button" class="btn-shop btn-shop1" data-toggle="dropdown">
                                        <i class="fa fa-shopping-cart" aria-hidden="true"></i> Kurv <span class="badge badge-pill badge-danger">${sessionScope.cartCupcakes.size()}</span>
                                    </button>
                                    <div class="dropdown-menu">
                                        <div class="row total-header-section">
                                            <div class="col-lg-6 col-sm-6 col-6">
                                                <i class="fa fa-shopping-cart" aria-hidden="true" style="color: white"></i> <span class="badge badge-pill badge-danger">${sessionScope.cartCupcakes.size()}</span>
                                            </div>
                                            <div class="col-lg-6 col-sm-6 col-6 total-section text-right">
                                                <p><button class="buttonClear" ></button></p>
                                            </div>
                                        </div>
                                        <c:forEach var="c" items="${sessionScope.cartCupcakes}">
                                            <div class="row cart-detail">
                                                <div class="col-lg-4 col-sm-4 col-4 cart-detail-img">
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
                                                <span class="price text-info">Pris: ${(c.botDTO.bot_price + c.topDTO.top_price)*c.quantity} DK</span> <span class="count"> Antal: ${c.quantity}</span>
                                                <form method="post">
                                                    <input type="number" id="quantityRefresh" name="quantityRefresh" value="${c.quantity}"
                                                           min="1" max="99">
                                                    <button class="btn btn-primary btn-update" formaction="CartRefresh" name="refresh" value="${c.cupcakeID}">Update</button>
                                                    <button class="btn btn-primary btn-remove" formaction="CartRefresh" name="refresh" value="101">x
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
                                                <button class="btn btn-primary btn-block" formaction="CartRefresh" name="refresh" value="103">Checkout</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </nav>
</header>



<div id="body" class="container mt-4" style="min-height: 400px">
    <h1><jsp:invoke fragment="header"/></h1>
    <jsp:doBody/>
</div>

<!-- Footer -->
<div class="container mt-3" >
    <hr/>
    <div class="row mt-4">
        <div class="col">
            Nørgaardsvej 30<br/>
            2800 Lyngby
        </div>
        <div class="col">
            <jsp:invoke fragment="footer"/><br/>
            <p>&copy; 2022 Cphbusiness</p>
        </div>
        <div class="col">
            Datamatikeruddannelsen<br/>
            2. semester forår 2022
        </div>
    </div>

</div>

</div>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/scripts/changeCupcakeImage.js" type="text/javascript"></script>

</body>
</html>
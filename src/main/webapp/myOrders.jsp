<%@ page contentType="text/html; charset=UTF-8;" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@page errorPage="error.jsp" isErrorPage="false" %>--%>


<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>

  <jsp:attribute name="footer">
    </jsp:attribute>

  <jsp:body>
    <c:if test="${sessionScope.user != null}">

      <h1>Kvitteringer</h1>

      <c:forEach var="o" items="${sessionScope.cupcakeOrders}">
        <div class="row total-header-section">
          <div class="col-lg-6 col-sm-6 col-6">
            <p>Order ID# ${o.orderID}</p>
          </div>
        </div>
        <c:forEach var="c" items="${o.cupcakesList}">
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
            </div>
          </div>
        </c:forEach>
      </c:forEach>

    </c:if>
    <c:if test="${sessionScope.user == null}">
      <meta http-equiv = "refresh" content = "0; url = login.jsp" />
    </c:if>
  </jsp:body>

</t:pagetemplate>

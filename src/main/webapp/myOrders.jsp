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

      <div class="d-flex flex-wrap">
        <c:forEach var="o" items="${sessionScope.cupcakeOrders}">
          <div class="card text-center m-2" style="width: 22rem;">
            <div class="card-header">
              <h4>ORDER ID#${o.orderID}</h4>
            </div>
          <div class="card-body">
          <c:forEach var="c" items="${o.cupcakesList}">
            <div class="col-lg-4 col-sm-4 col-4 cart-detail-img3">
              <div class="cart-upper-img3">
                <img src="images/Top/top_${c.topDTO.id}.png">
              </div>
              <div>
                <img src="images/Bot/bot_${c.botDTO.id}.png">
              </div>
            </div>
            <div class="textRow">
              <div class="">
                <h2 >Cupcake design #${c.cupcakeID}</h2>
                <p>Med ${c.botDTO.bot_flavor} bund og ${c.topDTO.top_flavor}
                  top</p>
              </div>
              <div class="">
                <span class="">Pris: ${(c.botDTO.bot_price + c.topDTO.top_price)*c.quantity} DK</span>
                <span class=""><span class=""> Antal: <span
                        class="">${c.quantity}</span></span></span>
              </div>
            </div>
          </c:forEach>
          </div>
          <div class="totalRow">
            <div class="">
              <div class="">Pris-Total</div>
              <div class="items">Items:</div>
            </div>
            <div class="total-amount">${o.total_price} DK</div>
          </div>
          </div>
        </c:forEach>
      </div>


    </c:if>


    <c:if test="${sessionScope.user == null}">
      <meta http-equiv = "refresh" content = "0; url = login.jsp" />
    </c:if>
  </jsp:body>

</t:pagetemplate>

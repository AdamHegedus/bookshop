<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

		<c:choose>
          <c:when test="${not empty shoppingCartModel.cartItems}">
            <table>
              <tr>
                <th><spring:message code="com.epam.bookshop.shopping_cart.title" /></th>
                <th><spring:message code="com.epam.bookshop.shopping_cart.author" /></th>
                <th><spring:message code="com.epam.bookshop.shopping_cart.format" /></th>
                <th><spring:message code="com.epam.bookshop.shopping_cart.quantity" /></th>
              </tr>

              <c:forEach var="cartItem" items="${shoppingCartModel.cartItems}">
                <tr>
                  <td><a href="<c:url value='${cartItem.book.detailsUrl}' />">${cartItem.book.title}</a></td>
                  <td>${cartItem.book.author}</td>
                  <td>${cartItem.book.bookFormat}</td>
                  <td>${cartItem.quantity}</td>
                </tr>
              </c:forEach>

            </table>
            <a href="<c:url value='${shoppingCartModel.clearShoppingCartUrl}' />"><spring:message code="com.epam.bookshop.shopping_cart.emptyCart" /></a>
          </c:when>
          <c:otherwise>
            <p><spring:message code="com.epam.bookshop.shopping_cart.empty" /></p>
          </c:otherwise>
        </c:choose>
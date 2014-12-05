<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

		<h2><spring:message code="com.epam.bookshop.book_details.title" /></h2>
        	<p>${bookDetailsModel.bookSummary.title}</p>
        <h2><spring:message code="com.epam.bookshop.book_details.author" /></h2>
        	<p>${bookDetailsModel.bookSummary.author}</p>
        <h2><spring:message code="com.epam.bookshop.book_details.synopsis" /></h2>
        	<p>${bookDetailsModel.bookDetails.synopsis}</p>
        <h2><spring:message code="com.epam.bookshop.book_details.stock" /></h2>
        	<p>${bookDetailsModel.bookDetails.stock}</p>
        <p>
        	<form:form modelAttribute="addShoppingCartBookItemRequest" servletRelativeAction="${bookDetailsModel.addToCartUrl}">
            	<form:hidden path="bookId" />
            	<button type="submit"><spring:message code="com.epam.bookshop.book_details.toCart" /></button>
          	</form:form>
        </p>
        <c:if test="${not empty bookDetailsModel.bookDetails.coverUrl}">
        	<h2><spring:message code="com.epam.bookshop.book_details.cover" /></h2>
        	<img src="<c:url value='${bookDetailsModel.bookDetails.coverUrl}' />" />
        </c:if>
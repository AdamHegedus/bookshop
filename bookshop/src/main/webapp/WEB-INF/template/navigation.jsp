<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="navigation">
	<ul>
		<li><a href="<c:url value='/' />"><spring:message code="com.epam.bookshop.home.nav" /></a></li>
		<li><a href="<c:url value='/shopping.html' />"><spring:message code="com.epam.bookshop.shopping.nav" /></a></li>
		<c:if test="${not empty homepageModel.adminUrl}">
			<li><a href="<c:url value='${homepageModel.adminUrl}' />"><spring:message code="com.epam.bookshop.add_book.nav" /></a></li>
		</c:if>
		<li><a href="<c:url value='/showShoppingCart.html' />"><spring:message code="com.epam.bookshop.shopping_cart.nav" /></a></li>
		<li>
			<div class="language-selector">
				<c:forEach var="languageSelector" items="${homepageModel.languageSelectors}">
					<a href="<c:url value='${languageSelector.languageSelectorUrl}' />">${languageSelector.languageCode}</a>
				</c:forEach>
			</div>
		<li>
		<li><a href="<c:url value='/users.html' />"><spring:message code="com.epam.bookshop.users.nav" /></a></li>
	</ul>
</div>
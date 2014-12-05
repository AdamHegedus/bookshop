<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div id="header">
	<div class="authentication">
		<c:if test="${not empty homepageModel.loginUrl}">
			<a href="<c:url value='${homepageModel.loginUrl}' />"><spring:message code="com.epam.bookshop.login.nav" /></a>
			<span> | </span>
			<a href="<c:url value='/addUserForm.html' />"><spring:message code="com.epam.bookshop.register.nav" /></a>  
		</c:if>

		<c:if test="${not empty homepageModel.logoutUrl}">
			<a href="<c:url value='${homepageModel.logoutUrl}' />"><spring:message code="com.epam.bookshop.logout.nav" /></a>
		</c:if>
	</div>

	<h1>Welcome in ${homepageModel.bookshopName}</h1>
</div>
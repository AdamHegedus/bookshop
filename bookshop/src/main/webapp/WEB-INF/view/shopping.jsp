<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

		<form:form modelAttribute="listBooksRequest" method="get">
        	<label for="title"><spring:message code="com.epam.bookshop.shopping.form.title" /></label>
        	<form:input path="title" type="text" id="title" placeholder="title" />
        	<button type="submit"><spring:message code="com.epam.bookshop.shopping.form.search" /></button>
        </form:form>
        <table>
        	<tr>
            	<th><spring:message code="com.epam.bookshop.shopping.form.title" /></th>
            	<th><spring:message code="com.epam.bookshop.shopping.form.author" /></th>
            	<th><spring:message code="com.epam.bookshop.shopping.form.format" /></th>
          	</tr>

        	<c:forEach var="book" items="${listBooksModel.books}">
            <tr>
            	<td><a href="<c:url value='${book.detailsUrl}' />">${book.title}</a></td>
              	<td>${book.author}</td>
              	<td>${book.bookFormat}</td>
            </tr>
          	</c:forEach>
        </table>
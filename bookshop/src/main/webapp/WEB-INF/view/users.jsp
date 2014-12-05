<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

		<form:form modelAttribute="listUsersRequest" method="get">
            <label for="username"><spring:message code="com.epam.bookshop.users.username" /></label>
            <form:input path="username" type="text" id="username" placeholder="Username" />
          <button type="submit"><spring:message code="com.epam.bookshop.users.search" /></button>
        </form:form>
        <table>
          <tr>
            <th><spring:message code="com.epam.bookshop.users.username" /></th>
            <th><spring:message code="com.epam.bookshop.users.email" /></th>
            <th><spring:message code="com.epam.bookshop.users.dob" /></th>
          </tr>

          <c:forEach var="user" items="${listUsersModel.users}">
            <tr>
              <td>${user.username}</td>
              <td>${user.email}</td>
              <td>${user.dateOfBirth}</td>
            </tr>
          </c:forEach>

        </table>
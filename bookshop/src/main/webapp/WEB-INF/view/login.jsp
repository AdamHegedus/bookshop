<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

		<form:form modelAttribute="loginRequest" action="j_spring_security_check">
        	<form:errors  element="div" cssClass="validation-error" />
        	<div class="form-group">
            <p>
            	<label for="input_username"><spring:message code="com.epam.bookshop.login.username" /></label>
            	<form:input path="username" id="input_username" placeholder="Username" />
            </p>
            <p>
            	<label for="input_password"><spring:message code="com.epam.bookshop.login.password" /></label>
            	<form:input path="password" id="input_password" placeholder="Password" />
            </p>
          	</div>
          	<p>
          		<button type="submit" class="btn btn-default"><spring:message code="com.epam.bookshop.login.login" /></button>
          	</p>
		</form:form>
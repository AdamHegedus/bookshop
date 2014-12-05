<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

        <c:if test="${not empty message}">
          <div class="alert alert-success">${message}</div>
        </c:if>
        <form:form modelAttribute="addUserRequest" action="addUserPost.html">
          <div class="form-group">
          	  <p>
                <label for="input_username"><spring:message code="com.epam.bookshop.register.username" /></label>
                <form:input path="username" id="input_username" placeholder="Username" />
                <form:errors path="username" element="div" cssClass="validation-error"/>                
              </p>                    
              <p>
                <label for="input_email"><spring:message code="com.epam.bookshop.register.email" /></label>
                <form:input path="email" id="input_email" placeholder="Email" />
                <form:errors path="email" element="div" cssClass="validation-error"/>                
              </p>              
              <p>
                <label for="input_password"><spring:message code="com.epam.bookshop.register.password" /></label>                                
                <form:input path="password" id="input_password" placeholder="Password" />
                <form:errors path="password" element="div" cssClass="validation-error" />                
              </p>
              <p>
                <label for="input_dateOfBirth"><spring:message code="com.epam.bookshop.register.dob" /></label>                                
                <form:input path="dateOfBirth" id="input_dateOfBirth" placeholder="Date Of Birth" />
                <form:errors path="dateOfBirth" element="div" cssClass="validation-error" />                
              </p>
		  </div>

          <p>
            <button type="submit" class="btn btn-default"><spring:message code="com.epam.bookshop.register.register" /></button>
          </p>
        </form:form>

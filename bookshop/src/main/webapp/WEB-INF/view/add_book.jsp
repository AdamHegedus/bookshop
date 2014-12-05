<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

        <c:if test="${not empty message}">
          <div class="alert alert-success">${message}</div>
        </c:if>
        <form:form modelAttribute="addBookRequest" action="addBookPost.html" enctype="multipart/form-data">
          <div class="form-group">
            <p>
              <label for="input_title"><spring:message code="com.epam.bookshop.add_book.title" /></label>
              <form:input path="title" id="input_title" placeholder="Title" />
              <form:errors path="title" element="div" cssClass="validation-error" />
            </p>
            <p>
              <label for="input_author"><spring:message code="com.epam.bookshop.add_book.author" /></label>
              <form:input path="author" id="input_author" placeholder="Author" />
              <form:errors path="author" element="div" cssClass="validation-error" />
            </p>
            <p>
              <label for="input_format"><spring:message code="com.epam.bookshop.add_book.format" /></label>
              <form:select path="format" id="input_format" items="${addBookFormModel.availableBookFormats}" />
            </p>
            <p>
              <label for="input_synopsis"><spring:message code="com.epam.bookshop.add_book.synopsis" /></label>
              <form:textarea path="synopsis" id="input_synopsis" placeholder="Synopsis" />
            </p>
            <p>
              <label for="cover"><spring:message code="com.epam.bookshop.add_book.cover" /></label>
              <form:input path="cover" type="file" id="cover" />
            </p>
            <p>
              <label for="input_stock"><spring:message code="com.epam.bookshop.add_book.stock" /></label>
              <form:textarea path="stock" id="input_stock" placeholder="Stock" />
            </p>

          </div>

          <p>
            <button type="submit" class="btn btn-default"><spring:message code="com.epam.bookshop.add_book.add" /></button>
          </p>
        </form:form>

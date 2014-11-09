<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping</title>
<link rel="stylesheet" href="http://www.maxdesign.com.au/wp-content/themes/maxdesign/stylesheets/sample.css" type="text/css" media="all">
</head>
<body>
  <div id="layout-one-fixed">
    <div id="container">
      <div id="header">
        <h1>Browse our books</h1>
      </div>
      <div id="navigation">
        <ul>
          <li><a href="<c:url value='/' />">Home</a></li>
          <li><a href="<c:url value='/shopping.html' />">Bookstore</a></li>
        </ul>
      </div>
      <div id="content">
        <table>
          <tr>
            <th>Title</th>
            <th>Author</th>
          </tr>

          <c:forEach var="book" items="${listBooksModel.books}">
            <tr>
              <td>${book.title}</td>
              <td>${book.author}</td>
            </tr>
          </c:forEach>

        </table>
      </div>
    </div>
  </div>
</body>
</html>
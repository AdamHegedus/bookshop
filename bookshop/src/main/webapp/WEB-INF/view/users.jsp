<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users</title>
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
          <li><a href="<c:url value='/addBookForm.html' />">Manage books</a></li>
          <li><a href="<c:url value='/users.html' />">List Users</a></li>
          <li><a href="<c:url value='/addUserForm.html' />">Manage users</a></li>
        </ul>
      </div>
      <div id="content">
        <form:form modelAttribute="listUsersRequest" method="get">
            <label for="email">Email</label>
            <form:input path="email" type="text" id="email" placeholder="Email" />
          <button type="submit">Search</button>
        </form:form>
        <table>
          <tr>
            <th>Email</th>
          </tr>

          <c:forEach var="user" items="${listUsersModel.users}">
            <tr>
              <td>${user.email}</td>
            </tr>
          </c:forEach>

        </table>
      </div>
    </div>
  </div>
</body>
</html>
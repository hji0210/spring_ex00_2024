<%--
  Created by IntelliJ IDEA.
  User: 한지인
  Date: 2024-05-15
  Time: 오전 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Title</title>
</head>
<body>


<h1>Page ${page}</h1>

<h2>${dto}</h2>



<hr/>
<ul>
    <%-- 리스트를 순회하며 각 항목을 출력 --%>
    <c:forEach var="item" items="${list}">
        <li>${item}</li>
    </c:forEach>
</ul>

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: monkey
  Date: 19/08/2019
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="pagination">
    <c:forEach begin="1" end="${pageInfo.totalPages}" varStatus="loop">
        <c:choose>
            <c:when test="${pageInfo.indexPage == loop.index}">
                <li class="active"><a href="javascript:void(0);">${loop.index}</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="javascript:void(0);" onclick="gotoPage(${loop.index});">${loop.index}</a></li>
            </c:otherwise>
        </c:choose>
        <li></li>
    </c:forEach>
</ul>

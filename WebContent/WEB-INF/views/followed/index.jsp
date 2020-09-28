<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>フォロワー</h2>
        <table id = "followed_list">
            <tbody>
                <tr>
                    <th>氏名</th>
                </tr>
                <c:forEach var="followed" items="${followed_list}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${followed.employee1.name}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagenation">
            (全 ${followed_list_count} 件)<br/>
            <c:forEach var="i" begin="1" end="${((followed_list_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}"/>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/follows/followedIndex?page=${i}' />"><c:out value="${i}"/></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>

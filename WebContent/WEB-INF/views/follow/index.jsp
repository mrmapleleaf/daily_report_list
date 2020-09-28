<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>フォロー中の従業員</h2>
    <table id="follow_list">
        <tbody>
            <tr>
                <th class = "follow_name">氏名</th>
            </tr>
            <c:forEach var = "follow" items = "${f_l}" varStatus = "status">
                <tr class = "row${status.count % 2}">
                    <td class = "follow_name"><c:out value = "${follow.employee2.name}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

        <div id = "pagination">
            (全 ${f_l_c} 件)<br/>
            <c:forEach var="i" begin="1" end="${((f_l_c - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}"/>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/follows/followIndex?page=${i}'/>"><c:out value="${i}"/></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>
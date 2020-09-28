<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name = "content">
        <h3>[タイムライン]</h3>
        <table id = "report_list">
            <tbody>
                <tr>
                    <th class="report_name">氏名</th>
                    <th class="report_date">日付</th>
                    <th class="report_title">タイトル</th>
                    <th class="report_action">操作</th>
                    <th class="report_like">いいね数</th>
                </tr>

                <c:forEach var="report" items="${f_a_r}" varStatus="status">
                   <tr class="row${status.count % 2}">
                       <td class="report_name"><c:out value="${report.employee.name}"/></td>
                       <td class="report_date"><fmt:formatDate value='${report.report_date}' pattern='yyyy/MM/dd'/></td>
                       <td class="report_title">${report.title}</td>
                       <td class="report_action"><a href="<c:url value='/reports/show?id=${report.id}' />">詳細を見る</a></td>
                       <c:choose>
                        <c:when test="${report.count_like > 0}">
                            <td class="report_like"><a href ="<c:url value='/likes/index?id=${report.id}' />"><c:out value="${report.count_like}" /></a></td>
                        </c:when>
                        <c:otherwise>
                            <td class="report_like"><c:out value="${report.count_like}"/></td>
                        </c:otherwise>
                    </c:choose>
                   </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            (全 ${f_a_r_c} 件)<br />
            <c:forEach var="i" begin="1" end="${((f_a_r_c - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" /> &nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/timeline/index?page=${i}' />"><c:out value="${i}" /></a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

    </c:param>
</c:import>


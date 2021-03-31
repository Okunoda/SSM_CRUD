<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/WEB-INF/comment/head.jsp"/>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>SSM-CURD</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary">增加</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <tr>
                    <td>#</td>
                    <td>lastName</td>
                    <td>email</td>
                    <td>salary</td>
                    <td>departmentName</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${pageInfo.list}" var="item">
                    <tr>
                        <td>${item.employeeId}</td>
                        <td>${item.lastName}</td>
                        <td>${item.email}</td>
                        <td>${item.salary}</td>
                        <td>${item.department.departmentName}</td>
                        <td>
                            <button class="btn-primary btn-sm">
                                <span class="glyphicon glyphicon-pencil"></span>
                                编辑
                            </button>
                            <button class="btn-danger btn-sm">
                                <span class="glyphicon glyphicon-trash"></span>
                                删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <%--    分页信息--%>
    <div class="row">
        <%--分页信息--%>
        <div class="col-md-5">
            当前第${pageInfo.pageNum}页，共有${pageInfo.pages}页，总计${pageInfo.total}条记录
        </div>
        <%--分页条--%>
        <div class="col-md-12 col-md-offset-8">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${pageInfo.isFirstPage!=true}">
                        <li><a href="${requestScope.path}emps/1">首页</a></li>
                        <li>
                            <a href="${requestScope.path}emps/${pageInfo.prePage}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach items="${pageInfo.navigatepageNums}" var="item">
                        <c:if test="${item==pageInfo.pageNum}">
                            <li class="active"><a href="${requestScope.path}emps/${item}">${item}</a></li>
                        </c:if>
                        <c:if test="${item!=pageInfo.pageNum}">
                            <li><a href="${requestScope.path}emps/${item}">${item}</a></li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${pageInfo.isLastPage!=true}">
                        <li>
                            <a href="${requestScope.path}emps/${pageInfo.nextPage}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                        <li><a href="${requestScope.path}emps/${pageInfo.pages}">末页</a></li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
</div>

</body>
</html>

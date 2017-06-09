<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>

<html>
<head>
    <title>书籍列表</title>
    <%@include file="common/head.jsp"%>
</head>
<body>
    <!-- 展示列表 -->
    <div class="container">
        <div class="panel panel-default">
            <!--头部-->
            <div class="panel-heading text-center">
                <h2>BookList</h2>
            </div>
            <!--列表体-->
            <div class="panel-body">
                <!-- 表单-->
                <table class="table table-hover">
                    <!--表头-->
                    <thead>
                        <tr>
                            <th>name</th>
                            <th>stock</th>
                            <th>start-time</th>
                            <th>end-time</th>
                            <th>create-time</th>
                            <!--<th>details</th>-->
                        </tr>
                    </thead>

                    <!-- 表体 -->
                    <tbody>
                        <c:forEach var="sk" items="${list}">
                            <tr>
                                    <td>${sk.name}</td>
                                    <td>${sk.number}</td>
                                    <td>
                                        <fmt:formatDate value="${sk.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${sk.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${sk.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>

                            </tr>
                        </c:forEach>
                    </tbody>

                </table>
            </div>
        </div>

    </div>
</body>
</html>

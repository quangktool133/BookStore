<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar navbar-expand-lg navbar-dark bg-dark" style="position: sticky; top: 0; z-index: 100">
    <nav class="container">
        <a class="navbar-brand"  href="${pageContext.request.contextPath}/home">Trang chủ</a>

        <ul class="navbar-nav mr-auto">

            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/cart">Giỏ hàng</a>
            </li>

            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/bill/history">Lịch sử mua hàng</a>
            </li>
        </ul>

        <c:if test="${sessionScope.USER_LOGGED_IN != null}">
            <a class="nav-link" style="color: white;">Xin chào, ${sessionScope.USER_LOGGED_IN.username}</a>
            <a class="nav-link" style="color: white;" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
        </c:if>

        <c:if test="${sessionScope.USER_LOGGED_IN == null}">
            <a class="nav-link" style="color: white;" href="${pageContext.request.contextPath}/login">Đăng nhập</a>
        </c:if>
    </nav>
</div>
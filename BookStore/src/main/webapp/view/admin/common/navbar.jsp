<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar navbar-expand-lg navbar-dark bg-dark" style="position: sticky; top: 0; z-index: 100">
    <nav class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/home">Trang chủ</a>

        <ul class="navbar-nav mr-auto">

            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/admin/book">Quản lý sách</a>
            </li>

            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/admin/discount">Quản lý phiếu giảm giá</a>
            </li>

            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
            </li>
        </ul>
    </nav>
</div>
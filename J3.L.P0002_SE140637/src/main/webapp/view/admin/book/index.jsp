<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quản lý sách | Book Store</title>
    <jsp:include page="../../common/styles.jsp"/>
</head>
<body>
<jsp:include page="../common/navbar.jsp"/>

<div style="background: #eef0f8">

    <div class="container p-0" style="background: white">
        <div class="font-weight-bold p-2" style="border-bottom: 1px solid #ebedf3">THÔNG TIN TÌM KIẾM</div>
        <div>
            <form class="d-flex flex-wrap justify-content-center p-3">
                <div class="form-group col-md-5">
                    <label>Tên sách: </label>
                    <input placeholder="Nhập tên sách" name="name" value="${param.name}" class="form-control" />
                </div>
                <div class="form-group col-md-5">
                    <label>Loại sách: </label>
                    <select class="form-control" name="idCategory">
                        <c:forEach varStatus="statusLoop" var="category" items="${categories}">
                            <option value="${category.id}" ${category.id == param.idCategory || statusLoop.index == 0 ? 'selected' : ''}>${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-md-5">
                    <label>Từ giá: </label>
                    <input type="number" placeholder="Nhập giá" name="fromPrice" value="${param.fromPrice}" class="form-control" />
                </div>
                <div class="form-group col-md-5">
                    <label>Đến giá: </label>
                    <input type="number" placeholder="Nhập giá" name="toPrice" value="${param.toPrice}" class="form-control" />
                </div>

                <div class="d-flex justify-content-center col-md-12">
                    <button class="btn btn-primary">Tìm kiếm</button>
                </div>
            </form>
        </div>
    </div>
    <div class="container p-0"style="background: white">
        <div class="d-flex justify-content-between align-items-center p-2" style="border-bottom: 1px solid #ebedf3">
            <div class="font-weight-bold">KẾT QUẢ TÌM KIẾM</div>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/book/create">Tạo mới sách</a>
        </div>
        <div class="p-2">
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Hình Ảnh</th>
                    <th scope="col">Tiêu đề</th>
                    <th scope="col">Tên</th>
                    <th scope="col">Giá tiền</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col" colspan="2"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach varStatus="statusLoop" var="book" items="${books}">
                    <tr>
                        <td style="vertical-align: middle; text-align: center">${statusLoop.index + 1}</td>
                        <td style="vertical-align: middle">
                            <img style="width: 98px; height: 98px" src="${pageContext.request.contextPath}${book.image}" alt="${book.name}"/>
                        </td>
                        <td style="vertical-align: middle">${book.title}</td>
                        <td style="vertical-align: middle; max-width: 500px;" class="text-dot" title="${book.name}">${book.name}</td>
                        <td style="vertical-align: middle">${book.priceVnd}</td>
                        <td style="vertical-align: middle">${book.quantity}</td>
                        <td style="vertical-align: middle">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/book/update?id=${book.id}">Cập nhật</a>
                        </td>
                        <td style="vertical-align: middle">
                            <button class="btn btn-danger" onclick="confirmBeforeRemove(${book.id})">Xóa</button>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>

</body>
<script>
    function confirmBeforeRemove(bookId) {
        const i = window.confirm("Bạn có chắc chắn muốn xóa không?")
        if (i) {
            window.location.href = '${pageContext.request.contextPath}/admin/book/remove?id='+bookId;
        }
    }
</script>
</html>

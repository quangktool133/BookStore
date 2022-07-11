<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Form sách | Book Store</title>
    <jsp:include page="../../common/styles.jsp"/>
</head>
<body style="background: #eef0f8">
<jsp:include page="../common/navbar.jsp"/>

<div class="d-flex align-items-center h-100">
    <div class="container" style="background: white">
        <form method="post" enctype="multipart/form-data" class="d-flex flex-wrap py-4">
            <input value="${book.id}" hidden name="id" />
            <input value="${book.image}" hidden name="image" />
            <div class="form-group col-md-6">
                <label for="title" class="required">Chủ đề</label>
                <input type="text" class="form-control" id="title" placeholder="Nhập chủ đề của sách" name="title" value="${book.title}">
            </div>

            <div class="form-group col-md-6">
                <label for="name" class="required">Tên sách</label>
                <input type="text" class="form-control" id="name" placeholder="Nhập tên của sách" name="name" value="${book.name}">
            </div>

            <div class="form-group col-md-6">
                <label for="price" class="required">Giá tiền</label>
                <input type="number" class="form-control" min="1" id="price" placeholder="Nhập giá của sách" name="price" value="${book.price}">
            </div>

            <div class="form-group col-md-6">
                <label for="author" class="required">Tác giá</label>
                <input type="text" class="form-control" id="author" placeholder="Nhập tác giả của sách" name="author" value="${book.author}">
            </div>

            <div class="form-group col-md-6">
                <label for="quantity" class="required">Số lượng</label>
                <input type="number" class="form-control" min="1" id="quantity" placeholder="Nhập số lượng của sách" name="quantity" value="${book.quantity}">
            </div>

            <div class="form-group col-md-6">
                <label for="category" class="required">Loại sách</label>
                <select class="form-control" id="category" name="categoryId">
                    <c:forEach var="category" items="${categories}" varStatus="statusLoop">
                        <option value="${category.id}" ${book.categoryId == category.id || statusLoop.index == 0 ? 'selected' : ''}>${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-6">
                <label for="description">Mô tả</label>
                <textarea class="form-control" id="description" rows="5" name="description">${book.description}</textarea>
            </div>

            <div class="form-group col-md-6">
                <label for="formFile" class="form-label required">Hình ảnh</label>
                <input class="form-control" type="file" id="formFile" name="imageBook">
            </div>

            <div class="col-md-12 d-flex justify-content-center">
                <button class="btn btn-primary">Lưu lại</button>
                <a class="btn btn-danger ml-3" href="${pageContext.request.contextPath}/admin/book">Hủy bỏ</a>
            </div>
        </form>
    </div>
</div>
</body>
<script>
    const e = '${error}';
    console.log(e);
    if (e) {
        toastr.error(e);
    }
</script>
</html>

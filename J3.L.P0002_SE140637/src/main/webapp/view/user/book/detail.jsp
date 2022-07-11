<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${ book.name } | Book Store</title>
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <jsp:include page="../../common/styles.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/user/book/css/book.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/user/book/css/detail.css">
</head>
<body style="background: #eef0f8">
<jsp:include page="../common/navbar.jsp"/>
<c:if test="${book == null}">
    <div class="d-flex justify-content-center">
        <span class="h4 error">Không tìm thấy sách!!!</span>
    </div>
</c:if>
<c:if test="${book != null}">
    <div class="container bg-white detail d-flex">
        <div class="book-image col-md-4 d-flex flex-column">
            <div style="position: relative">
                <c:if test="${book.quantity == 0}">
                    <div class="book-label" style="--primary: #f35959">Hết hàng</div>
                </c:if>
                <c:if test="${book.quantity > 0}">
                    <div class="book-label" style="--primary: #08b308">Còn hàng</div>
                </c:if>
            </div>
            <img class="w-100 h-100" src="${pageContext.request.contextPath}${book.image}" alt="${book.name}" }/>
            <div class="d-flex align-items-center mt-4" style="gap: 10px">
                <div class="shareable mr-2">Chia sẻ:</div>
                <img src="https://frontend.tikicdn.com/_desktop-next/static/img/pdp_revamp_v2/social-facebook.svg" />
                <img src="https://frontend.tikicdn.com/_desktop-next/static/img/pdp_revamp_v2/social-messenger.svg" />
                <img src="https://frontend.tikicdn.com/_desktop-next/static/img/pdp_revamp_v2/social-pinterest.svg" />
                <img src="https://frontend.tikicdn.com/_desktop-next/static/img/pdp_revamp_v2/social-twitter.svg" />
                <img src="https://frontend.tikicdn.com/_desktop-next/static/img/pdp_revamp_v2/social-copy.svg" />
            </div>
        </div>
        <div class="book-info col-md-8 d-flex flex-column">
            <span class="book-title">Chủ đề:&ensp;${book.title}</span>
            <span class="detail-book-name font-weight-bold">${book.name}</span>
            <span>Tác giả:&ensp;<a href="#">${book.author}</a> </span>
            <div class="d-flex align-items-center">
                <div>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                </div>
                <span class="book-quantity">&ensp;|&ensp;Số lượng: ${book.quantity}</span>
            </div>
            <div class="container-price">
                <span class="detail-book-price">${book.priceVnd}</span>
            </div>
            <form class="mt-2" action="${pageContext.request.contextPath}/cart/create" method="post">
                <input name="id" value="${book.id}" hidden />
                <div>
                    <span class="label-quantity">Số lượng</span>
                    <div class="d-flex">
                        <button type="button" class="button btn-subtract">
                            <img src="https://frontend.tikicdn.com/_desktop-next/static/img/pdp_revamp_v2/icons-remove.svg" />
                        </button>
                        <input value="${param.quantity == null ? 1 : param.quantity}" min="1" max="${book.quantity}" name="quantity" class="input" onkeydown="return false"/>
                        <button type="button" class="button btn-plus">
                            <img src="https://frontend.tikicdn.com/_desktop-next/static/img/pdp_revamp_v2/icons-add.svg" />
                        </button>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary mt-2 col-md-4 btn-buy">
                    Chọn mua
                </button>
            </form>
        </div>
    </div>

    <div class="container detail pt-0">
        <div class="similar-book">Sản phẩm tương tự</div>
        <div class="similar-book-container">
            <c:forEach varStatus="statusLoop" var="book" items="${booksSimilarAuthor}">
                <a class="col-md-3 book py-3" href="${pageContext.request.contextPath}/book/detail?id=${book.id}" target="_blank">
                    <div class="d-flex flex-column">
                        <img class="book-image" src="${pageContext.request.contextPath}${book.image}"/>
                        <span class="book-name">${book.name} </span>
                        <span class="book-author">by ${book.author}</span>
                        <span class="book-title">${book.title}</span>
                        <div class="d-flex align-items-center">
                            <div>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                            </div>
                            <span class="book-quantity">&ensp;|&ensp;Số lượng: ${book.quantity}</span>
                        </div>
                        <span class="book-price">${book.priceVnd}</span>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>

    <div class="container detail pt-0">
        <div class="detail-info-label">Thông Tin Chi Tiết</div>
        <div class="ml-3">
            <table>
                <tbody>
                <tr>
                    <td>Tên tác giả</td>
                    <td>${book.author}</td>
                </tr>
                <tr>
                    <td>Mô tả</td>
                    <td>${book.description}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="container detail pt-0">
        <div class="similar-book">Sản phẩm khác</div>
        <div class="similar-book-container">
            <c:forEach varStatus="statusLoop" var="book" items="${booksAuthorElse}">
                <a class="col-md-3 book py-3" href="${pageContext.request.contextPath}/book/detail?id=${book.id}" target="_blank">
                    <div class="d-flex flex-column">
                        <img class="book-image" src="${pageContext.request.contextPath}${book.image}"/>
                        <span class="book-name">${book.name} </span>
                        <span class="book-author">by ${book.author}</span>
                        <span class="book-title">${book.title}</span>
                        <div class="d-flex align-items-center">
                            <div>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                            </div>
                            <span class="book-quantity">&ensp;|&ensp;Số lượng: ${book.quantity}</span>
                        </div>
                        <span class="book-price">${book.priceVnd}</span>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</c:if>
</body>
<script src="https://kit.fontawesome.com/68830dcf1e.js" crossorigin="anonymous"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath}/view/user/book/js/detail.js"></script>
<script>
    const error = '${error}';
    if (error) {
        toastr.error(error);
    }
</script>
</html>

package com.bookstore.service;

import com.bookstore.constant.AppConstant;
import com.bookstore.constant.PaymentMethodEnum;
import com.bookstore.dao.BillDAO;
import com.bookstore.dao.BillDetailDAO;
import com.bookstore.dto.BillDTO;
import com.bookstore.dto.BillDetailDTO;
import com.bookstore.dto.CartDTO;
import com.bookstore.dto.ResultDTO;
import com.bookstore.entity.Bill;
import com.bookstore.entity.BillDetail;
import com.bookstore.entity.Book;
import com.bookstore.entity.Discount;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BillService {

    private BillDetailDAO billDetailDAO;
    private BillDAO billDAO;
    private DiscountService discountService;
    private BookService bookService;
    private CartService cartService;

    public BillService() {
        this.billDAO = new BillDAO();
        this.billDetailDAO = new BillDetailDAO();
        this.discountService = new DiscountService();
        this.bookService = new BookService();
        this.cartService = new CartService();
    }

    public void update(Bill bill) {
        this.billDAO.update(bill);
    }

    public List<BillDTO> search(Bill bill) {
        return this.billDAO.search(bill).stream()
                .map(b -> {
                    List<BillDetailDTO> billDetailDTOS = this.billDetailDAO.findByBillId(b.getId()).stream()
                            .map(billDetail -> {
                                Book book = this.bookService.findById(billDetail.getBookId());
                                return new BillDetailDTO(billDetail, book);
                            }).collect(Collectors.toList());
                    return new BillDTO(b, billDetailDTOS);
                }).collect(Collectors.toList());
    }

    public ResultDTO checkout(List<CartDTO> carts, String username, String discountCode, PaymentMethodEnum paymentMethodEnum) {
        try {
            Discount discount = null;
            boolean presentDiscountCode = Objects.nonNull(discountCode) && !discountCode.isEmpty();

            if (Objects.isNull(carts) || carts.isEmpty()) {
                return new ResultDTO(null, true, "Chưa có sản phẩm trong giỏ hàng!");
            }

            if (presentDiscountCode) {
                discount = this.discountService.findByCode(discountCode);
                if (Objects.isNull(discount)) {
                    return new ResultDTO(null, true, "Mã giảm giá không tồn tại");
                }

                if (AppConstant.STATUS_DISCOUNT_INACTIVE.equals(discount.getStatus())) {
                    return new ResultDTO(null, true, "Mã giảm giá đã được sử dụnng");
                }

                if (discount.getExpireDate().getTime() < new java.util.Date().getTime()) {
                    return new ResultDTO(null, true, "Mã giảm giá đã hết hạn");
                }
            }

            Float totalPrice = carts.stream()
                    .map(cartDTO -> cartDTO.getQuantity() * cartDTO.getBook().getPrice())
                    .reduce(0.0F, (total, current) -> total + current);

            if (presentDiscountCode) {
                totalPrice = totalPrice - (totalPrice * discount.getPercent() / 100);
            }

            Bill bill = new Bill();
            bill.setCreatedDate(new Date(System.currentTimeMillis()));
            bill.setUsername(username);
            bill.setTotal(totalPrice);
            bill.setPaymentMethod(paymentMethodEnum.method());
            bill.setDiscountCode(presentDiscountCode ? discountCode : null);
            int id = this.billDAO.insert(bill);
            bill.setId(id);

            if (presentDiscountCode) {
                this.discountService.updateStatus(discountCode);
            }

            for (CartDTO cartDTO : carts) {
                BillDetail billDetail = new BillDetail();
                billDetail.setBillId(bill.getId());
                billDetail.setPrice(cartDTO.getBook().getPrice());
                billDetail.setBookId(cartDTO.getBook().getId());
                billDetail.setSubtotal(cartDTO.getQuantity() * cartDTO.getBook().getPrice());
                billDetail.setQuantity(cartDTO.getQuantity());
                this.billDetailDAO.insert(billDetail);

                ResultDTO resultRemoveCart = this.cartService.remove(cartDTO.getId());
                if (resultRemoveCart.isError()) {
                    return resultRemoveCart;
                }

                Book book = this.bookService.findById(cartDTO.getBook().getId());
                book.setQuantity(book.getQuantity() - cartDTO.getQuantity());
                ResultDTO<Book> update = this.bookService.update(book);
                if (update.isError()) {
                    return update;
                }
            }

            return new ResultDTO(bill, false, "Thanh toán thành công");
        } catch(Exception ex) {
            return new ResultDTO(null, true, "Thanh toán thất bại");
        }
    }
}
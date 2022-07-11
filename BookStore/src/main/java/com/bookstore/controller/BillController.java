package com.bookstore.controller;

import com.bookstore.constant.AppConstant;
import com.bookstore.constant.BillEndpointConstant;
import com.bookstore.constant.PaymentMethodEnum;
import com.bookstore.dto.BillDTO;
import com.bookstore.dto.CartDTO;
import com.bookstore.dto.ResultDTO;
import com.bookstore.entity.Bill;
import com.bookstore.entity.Book;
import com.bookstore.entity.User;
import com.bookstore.service.BillService;
import com.bookstore.service.CartService;
import com.bookstore.service.DiscountService;
import com.bookstore.service.PaypalService;
import com.bookstore.util.DataUtil;
import com.bookstore.util.ReflectionUtil;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(value = {
        BillEndpointConstant.BILL_CHECKOUT_ENDPOINT,
        BillEndpointConstant.BILL_CHECKOUT_PAYPAL_ENDPOINT,
        BillEndpointConstant.BILL_GET_ITEM_PAYPAL,
        BillEndpointConstant.BILL_HISTORY_ENDPOINT
})
public class BillController extends HttpServlet {

    private static final String PATH_UI_CART = "/view/user/history/index.jsp";

    private BillService billService;
    private CartService cartService;
    private DiscountService discountService;
    private PaypalService paypalService;

    @Override
    public void init() throws ServletException {
        this.billService = new BillService();
        this.cartService = new CartService();
        this.discountService = new DiscountService();
        this.paypalService = new PaypalService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String endpoint = req.getServletPath();
        User user = (User) req.getSession().getAttribute(AppConstant.SESSION_USER);


        if (BillEndpointConstant.BILL_HISTORY_ENDPOINT.equals(endpoint)) {
            Bill bill = ReflectionUtil.requestParamToObject(req, Bill.class);
            bill.setUsername(user.getUsername());
            List<BillDTO> histories = this.billService.search(bill);
            req.setAttribute("histories", histories);
            req.setAttribute("totalPrice", DataUtil.formatCurrency(histories.stream().map(BillDTO::getTotal).reduce(0.0F, (total, current) -> total + current)));
            req.getRequestDispatcher(PATH_UI_CART).forward(req, resp);
        }

        if (BillEndpointConstant.BILL_CHECKOUT_ENDPOINT.equals(endpoint)) {

            List<CartDTO> carts = this.cartService.findByUsername(user.getUsername());
            ResultDTO resultCheckout = this.billService.checkout(carts, user.getUsername(), req.getParameter("discountCode"), PaymentMethodEnum.NORMAL);
            if (resultCheckout.isError()) {
                this.handleCheckoutError(req, resp, carts, resultCheckout.getMessage());
                return;
            }
            resp.sendRedirect(req.getContextPath()+"/bill/history");
        }

        if (BillEndpointConstant.BILL_CHECKOUT_PAYPAL_ENDPOINT.equals(endpoint)) {
            String discountCode = req.getParameter("discountCode");
            List<CartDTO> carts = this.cartService.findByUsername(user.getUsername());
            ResultDTO<String> result = this.paypalService.checkout(carts, user, discountCode);
            if (result.isError()) {
                this.handleCheckoutError(req, resp, carts, result.getMessage());
                return;
            }
            resp.sendRedirect(result.getData());
            return;
        }

        if (BillEndpointConstant.BILL_GET_ITEM_PAYPAL.equals(endpoint)) {
            String paymentId = req.getParameter("paymentId");
            ResultDTO<Payment> resultGetItemPaypal = this.paypalService.getPaymentDetails(paymentId);

            if (resultGetItemPaypal.isError()) {
                this.handleCheckoutError(req, resp, this.cartService.findByUsername(user.getUsername()), resultGetItemPaypal.getMessage());
                return;
            }

            Transaction transaction = resultGetItemPaypal.getData().getTransactions().get(0);

            List<CartDTO> carts = transaction.getItemList().getItems().stream()
                    .map(item -> {
                        String[] cartIdAndBookId = item.getDescription().split("\\,");
                        CartDTO cartDTO = new CartDTO();
                        cartDTO.setId(DataUtil.safeToInt(cartIdAndBookId[0]));
                        cartDTO.setQuantity(DataUtil.safeToInt(item.getQuantity()));
                        Book book = new Book();
                        book.setId(DataUtil.safeToInt(cartIdAndBookId[1]));
                        book.setPrice((float) DataUtil.safeToDouble(item.getPrice()));
                        cartDTO.setBook(book);
                        return cartDTO;
                    }).collect(Collectors.toList());

            ResultDTO<Bill> resultCheckout = this.billService.checkout(carts, user.getUsername(), transaction.getDescription(), PaymentMethodEnum.PAYPAL);
            if (resultCheckout.isError()) {
                this.handleCheckoutError(req, resp, carts, resultCheckout.getMessage());
                return;
            }
            Bill bill = resultCheckout.getData();
            bill.setDiscountCode(transaction.getDescription());
            this.billService.update(bill);
            resp.sendRedirect(req.getContextPath()+BillEndpointConstant.BILL_HISTORY_ENDPOINT);
        }
   }

   private void handleCheckoutError(HttpServletRequest req, HttpServletResponse resp, List<CartDTO> carts, String message) throws ServletException, IOException {
       String totalPrice = DataUtil.formatCurrency(carts.stream().map(CartDTO::getSubtotal).reduce(0.0F, (total, current) -> total + current));
       req.setAttribute("error", message);
       req.setAttribute("carts", carts);
       req.setAttribute("totalPrice", totalPrice);
       req.setAttribute("discountCards", this.discountService.findByStatusActive());
       req.setAttribute("totalBook", carts.stream().map(CartDTO::getQuantity).reduce(0, (total, current) -> total + current));
       req.getRequestDispatcher(CartController.PATH_UI_LIST_CART).forward(req, resp);
   }

    @Override
    public void destroy() {
        this.billService = null;
        this.cartService = null;
        this.discountService = null;
        this.paypalService = null;
    }
}
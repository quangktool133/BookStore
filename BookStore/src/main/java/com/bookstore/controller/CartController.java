package com.bookstore.controller;

import com.bookstore.constant.AppConstant;
import com.bookstore.constant.CartEndpointConstant;
import com.bookstore.dto.CartDTO;
import com.bookstore.dto.ResultDTO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Cart;
import com.bookstore.entity.User;
import com.bookstore.service.BookService;
import com.bookstore.service.CartService;
import com.bookstore.service.DiscountService;
import com.bookstore.util.DataUtil;
import com.bookstore.util.ReflectionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = {
        CartEndpointConstant.CART_INSERT_ENDPOINT,
        CartEndpointConstant.CART_UPDATE_ENDPOINT,
        CartEndpointConstant.CART_REMOVE_ENDPOINT,
        CartEndpointConstant.CART_GET_ALL_ENDPOINT
})
public class CartController extends HttpServlet {

    public final static String PATH_UI_LIST_CART = "/view/user/cart/index.jsp";

    private CartService cartService;
    private BookService bookService;
    private DiscountService discountService;

    @Override
    public void init() throws ServletException {
        this.cartService = new CartService();
        this.bookService = new BookService();
        this.discountService = new DiscountService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String endpoint = req.getServletPath();
        User user = (User) req.getSession().getAttribute(AppConstant.SESSION_USER);

        if (CartEndpointConstant.CART_GET_ALL_ENDPOINT.equals(endpoint)) {
            this.getInfoCart(req, resp, user);

        }

        if (CartEndpointConstant.CART_REMOVE_ENDPOINT.equals(endpoint)) {
            String cartId = req.getParameter("id");
            ResultDTO result = this.cartService.remove(DataUtil.safeToInt(cartId));
            if (result.isError()) {
                req.setAttribute("error", result.getMessage());
                req.setAttribute("carts", this.cartService.findByUsername(user.getUsername()));
                req.getRequestDispatcher(PATH_UI_LIST_CART).forward(req, resp);
                return;
            }
            resp.sendRedirect(req.getContextPath()+CartEndpointConstant.CART_GET_ALL_ENDPOINT);
        }

        if (CartEndpointConstant.CART_UPDATE_ENDPOINT.equals(endpoint)) {
            Cart cart = ReflectionUtil.requestParamToObject(req, Cart.class);
            ResultDTO<Cart> result = this.cartService.updateById(cart);
            if (result.isError()) {
                req.setAttribute("error", result.getMessage());
                this.getInfoCart(req, resp, user);
                return;
            }
            resp.sendRedirect(req.getContextPath() + CartEndpointConstant.CART_GET_ALL_ENDPOINT);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String endpoint = req.getServletPath();
        User user = (User) req.getSession().getAttribute(AppConstant.SESSION_USER);

        if (CartEndpointConstant.CART_INSERT_ENDPOINT.equals(endpoint)) {
            Book book = ReflectionUtil.requestParamToObject(req, Book.class);
            ResultDTO<Cart> result = this.cartService.insert(book, user.getUsername());
            if (result.isError()) {
                Book foundBook = this.bookService.findById(book.getId());
                req.setAttribute("error", result.getMessage());
                req.setAttribute("book", foundBook);
                req.setAttribute("booksSimilarAuthor", this.bookService.findByAuthor(foundBook.getAuthor()));
                req.setAttribute("booksAuthorElse", this.bookService.findByAuhorElse(foundBook.getAuthor()));
                req.getRequestDispatcher(BookController.PATH_UI_BOOK_DETAIL).forward(req, resp);
                return;
            }
            resp.sendRedirect(req.getContextPath()+CartEndpointConstant.CART_GET_ALL_ENDPOINT);

        }
    }

    private void getInfoCart(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        List<CartDTO> carts = this.cartService.findByUsername(user.getUsername());
        String totalPrice = DataUtil.formatCurrency(carts.stream().map(CartDTO::getSubtotal).reduce(0.0F, (total, current) -> total + current));
        req.setAttribute("carts", carts);
        req.setAttribute("totalPrice", totalPrice);
        req.setAttribute("discountCards", this.discountService.findByStatusActive());
        req.setAttribute("totalBook", carts.stream().map(CartDTO::getQuantity).reduce(0, (total, current) -> total + current));
        req.getRequestDispatcher(PATH_UI_LIST_CART).forward(req, resp);
    }

    @Override
    public void destroy() {
        this.cartService = null;
        this.bookService = null;
        this.discountService = null;
    }
}
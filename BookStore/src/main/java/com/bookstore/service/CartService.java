package com.bookstore.service;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CartDAO;
import com.bookstore.dto.CartDTO;
import com.bookstore.dto.ResultDTO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Cart;
import com.bookstore.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    private CartDAO cartDAO;
    private BookDAO bookDAO;

    public CartService() {
        this.cartDAO = new CartDAO();
        this.bookDAO = new BookDAO();
    }

    public List<CartDTO> findByUsername(String username) {
        return this.cartDAO.findByUsername(username).stream()
                .map(cart -> {
                    CartDTO cartDTO = new CartDTO(cart);
                    cartDTO.setBook(this.bookDAO.findById(cart.getBookId()));
                    return cartDTO;
                }).collect(Collectors.toList());
    }

    public List<CartDTO> findByUsernameAndIds(String username, List<String> ids) {
        try {
            return this.cartDAO.findByUsernameAndIds(username, ids).stream()
                    .map(cart -> {
                        CartDTO cartDTO = new CartDTO(cart);
                        cartDTO.setBook(this.bookDAO.findById(cart.getBookId()));
                        return cartDTO;
                    }).collect(Collectors.toList());
        } catch(Exception ex) {
            return new ArrayList<>();
        }
    }

    public ResultDTO<Cart> insert(Book book, String username) {
        try {
            logger.info("add book to cart: {}", book);

            Cart cart = this.cartDAO.findByUsernameAndBookId(username, book.getId());
            if (Objects.nonNull(cart)) {
                cart.setQuantity(cart.getQuantity() + book.getQuantity());
                return this.updateById(cart);
            }

            cart = new Cart();
            cart.setBookId(book.getId());
            cart.setUsername(username);
            cart.setQuantity(book.getQuantity());

            String messsageValidate = Validator.validate(cart);
            if (Objects.nonNull(messsageValidate)) {
                return new ResultDTO<>(null, true, messsageValidate);
            }

            Book foundBook = this.bookDAO.findById(book.getId());
            if (Objects.isNull(foundBook)) {
                logger.error("not found bool: {}", book);
                return new ResultDTO<>(null, true, "Kh??ng t??m th???t s??ch: "+book.getId());
            }

            if (foundBook.getQuantity() < book.getQuantity()) {
                logger.error("not enough quantity for book");
                return new ResultDTO<>(null, true, "Kh??ng ????? s??? l?????ng s???n ph???m");
            }

            int id = this.cartDAO.insert(cart);
            cart.setId(id);
            logger.info("add book to cart successfully");
            return new ResultDTO<Cart>(cart, false, "Th??m s??ch v??o gi??? h??ng th??nh c??ng");
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResultDTO<Cart>(null, true, "Th??m s??ch v??o gi??? h??ng th???t b???i");
        }
    }

    public ResultDTO<Cart> updateById(Cart cart) {
        try {
            logger.info("update quantity for cart: {}", cart);

            if (cart.getQuantity() == 0) {
                return new ResultDTO<>(null, true, "S??? l?????ng kh??ng th??? nh??? h??n 1");
            }

            Cart foundCart = this.cartDAO.findById(cart.getId());
            if (Objects.isNull(foundCart)) {
                logger.error("not found cart: {}", cart);
                return new ResultDTO<>(null, true, "Kh??ng t??m th???y cart: "+cart.getId());
            }

            Book book = this.bookDAO.findById(foundCart.getBookId());
            if (Objects.isNull(book)) {
                logger.error("not found book: {}", cart.getBookId());
                return new ResultDTO<>(null, true, "Kh??ng t??m th???y s???n ph???m: " + foundCart.getBookId());
            }

            if (book.getQuantity() < cart.getQuantity()) {
                logger.error("not enough quantity for cart");
                return new ResultDTO<>(null, true, "Kh??ng ????? s??? l?????ng s??ch");
            }

            this.cartDAO.update(cart);
            logger.info("update quantity for cart successfully");
            return new ResultDTO<>(cart, false, "C???p nh???t gi??? h??ng th??nh c??ng");
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResultDTO<>(null, true, "C???p nh???t gi??? h??ng th???t b???i");
        }
    }

    public ResultDTO remove(Integer cartId) {
        try {
            logger.info("remove cart: {}", cartId);
            Cart cart = this.cartDAO.findById(cartId);
            if (Objects.isNull(cart)) {
                return new ResultDTO(null, true, "Kh??ng t??m th???y gi??? h??ng: " + cartId);
            }

            this.cartDAO.deleteById(cartId);
            logger.info("remove cart successfully");
            return new ResultDTO(cart, false, "X??a gi??? h??ng th??nh c??ng");
        } catch(Exception ex) {
            return new ResultDTO(null, true, "X??a gi??? h??ng th???t b???i");
        }

    }
}
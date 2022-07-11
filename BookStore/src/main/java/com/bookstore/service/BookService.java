package com.bookstore.service;

import com.bookstore.constant.StatusBookEnum;
import com.bookstore.dao.BookDAO;
import com.bookstore.dto.BookDTO;
import com.bookstore.dto.ResultDTO;
import com.bookstore.entity.Book;
import com.bookstore.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private BookDAO bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
    }

    public List<Book> search(BookDTO book) {
        try {
            logger.info("search book: {}", book);
            return this.bookDAO.searchBook(book);
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ArrayList<>();
        }
    }

    public List<Book> findByAuthor(String author) {
        return this.bookDAO.findBookByAuthor(author);
    }

    public List<Book> findByAuhorElse(String author) {
        return this.bookDAO.findByAuthorElse(author);
    }

    public Book findById(Integer id) {
        return this.bookDAO.findById(id);
    }

    public ResultDTO<Book> save(Book book) {
        try {
            logger.info("save book: {}", book);
            book.setStatus(StatusBookEnum.ACTIVE.getStatus());

            String messageValidate = Validator.validate(book);
            if (Objects.nonNull(messageValidate)) {
                return new ResultDTO(null, true, messageValidate);
            }
            int id = this.bookDAO.insert(book);
            book.setId(id);
            return new ResultDTO<>(book, false, "Tạo mới sách thành công");
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResultDTO<>(null, true, "Tạo mới sách thất bại");
        }
    }

    public ResultDTO<Book> update(Book book) {
        try {
            logger.info("update book: {}", book);

            Book foundBook = this.findById(book.getId());
            if (Objects.isNull(foundBook)) {
                return new ResultDTO<>(null, true, "Không tìm thấy book");
            }
            if (Objects.isNull(book.getStatus())) {
                book.setStatus(StatusBookEnum.ACTIVE.getStatus());
            }
            String messageValidate = Validator.validate(book);
            if (Objects.nonNull(messageValidate)) {
                return new ResultDTO<>(null, true, messageValidate);
            }
            this.bookDAO.updateById(book);
            return new ResultDTO<>(book, false, "Cập nhật thành công");
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResultDTO<>(null, true, "Cập nhật sách thất bại");
        }
    }

    public ResultDTO<Book> delete(Integer id) {
        try {
            Book book = this.bookDAO.findById(id);
            if (Objects.isNull(book)) {
                return new ResultDTO<>(null, true, "Không tìm thấy sách");
            }
            book.setStatus(StatusBookEnum.INACTIVE.getStatus());
            return this.update(book);
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResultDTO<>(null, true, "Xóa sách thất bại");
        }
    }
}
package com.bookstore.controller;

import com.bookstore.constant.BookEndpointConstant;
import com.bookstore.dto.BookDTO;
import com.bookstore.dto.ResultDTO;
import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;
import com.bookstore.service.ImageService;
import com.bookstore.util.DataUtil;
import com.bookstore.util.ReflectionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(value = {
        BookEndpointConstant.BOOK_SEARCH_ENDPOINT,
        BookEndpointConstant.BOOK_INSERT_ENDPOINT,
        BookEndpointConstant.BOOK_UPDATE_ENDPOINT,
        BookEndpointConstant.BOOK_REMOVE_ENDPOINT,
        BookEndpointConstant.BOOK_DETAIL_ENDPOINT,
        BookEndpointConstant.BOOK_ADMIN_SEARCH_ENDPOINT
})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class BookController extends HttpServlet {

    public static final String PATH_UI_USER_BOOK = "/view/user/book/index.jsp";
    public static final String PATH_UI_ADMIN_BOOK = "/view/admin/book/index.jsp";
    public static final String PATH_UI_FORM_BOOK = "/view/admin/book/form.jsp";
    public static final String PATH_UI_BOOK_DETAIL = "/view/user/book/detail.jsp";

    private BookService bookService;
    private CategoryService categoryService;
    private ImageService imageService;

    @Override
    public void init() throws ServletException {
        this.bookService = new BookService();
        this.categoryService = new CategoryService();
        this.imageService = new ImageService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String endpoint = req.getServletPath();

        if (BookEndpointConstant.BOOK_SEARCH_ENDPOINT.equals(endpoint) || BookEndpointConstant.BOOK_ADMIN_SEARCH_ENDPOINT.equals(endpoint)) {
            this.getAllBook(req, resp);
            return;
        }

        if (BookEndpointConstant.BOOK_INSERT_ENDPOINT.equals(endpoint)) {
            this.openBookForm(req, resp);
            return;
        }

        if (BookEndpointConstant.BOOK_UPDATE_ENDPOINT.equals(endpoint)) {
            this.openBookForm(req, resp);
            return;
        }

        if (BookEndpointConstant.BOOK_REMOVE_ENDPOINT.equals(endpoint)) {
            this.removeBook(req, resp);
        }

        if (BookEndpointConstant.BOOK_DETAIL_ENDPOINT.equals(endpoint)) {
            this.getBookDetail(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String endpoint = req.getServletPath();

        if (BookEndpointConstant.BOOK_INSERT_ENDPOINT.equals(endpoint)) {
            this.insertBook(req, resp);
        }

        if (BookEndpointConstant.BOOK_UPDATE_ENDPOINT.equals(endpoint)) {
            this.updateBook(req, resp);
        }
    }

    private void getBookDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("id");
        Book book = this.bookService.findById(DataUtil.safeToInt(bookId));
        request.setAttribute("book", book);
        request.setAttribute("booksSimilarAuthor", this.bookService.findByAuthor(book.getAuthor()));
        request.setAttribute("booksAuthorElse", this.bookService.findByAuhorElse(book.getAuthor()));
        request.getRequestDispatcher(PATH_UI_BOOK_DETAIL).forward(request, response);
    }

    private void getAllBook(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        BookDTO bookDTO = ReflectionUtil.requestParamToObject(req, BookDTO.class);
        List<Book> resultSearch = this.bookService.search(bookDTO);
        req.setAttribute("books", resultSearch);
        req.setAttribute("categories", this.categoryService.findAll());
        if (req.getServletPath().equals(BookEndpointConstant.BOOK_ADMIN_SEARCH_ENDPOINT)) {
            req.getRequestDispatcher(PATH_UI_ADMIN_BOOK).forward(req, response);
            return;
        }
        req.getRequestDispatcher(PATH_UI_USER_BOOK).forward(req, response);
    }

    private void openBookForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object id = req.getParameter("id");
        Book book = Objects.isNull(id) ? new Book() : this.bookService.findById(DataUtil.safeToInt(id));
        req.setAttribute("book", book);
        req.setAttribute("categories", this.categoryService.findAll());
        req.getRequestDispatcher(PATH_UI_FORM_BOOK).forward(req, resp);
    }

    private void insertBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = ReflectionUtil.requestParamToObject(req, Book.class);
        String imagePath = this.handleImage(req, book);
        if (Objects.nonNull(imagePath)) {
            book.setImage(imagePath);
        }
        ResultDTO<Book> result = this.bookService.save(book);
        if (result.isError()) {
            this.handleError(req, resp, result, book);
            return;
        }
        resp.sendRedirect(req.getContextPath() + BookEndpointConstant.BOOK_ADMIN_SEARCH_ENDPOINT);
    }

    private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Book book = ReflectionUtil.requestParamToObject(req, Book.class);
        String imageBook = this.handleImage(req, book);
        if (Objects.nonNull(imageBook)) {
            book.setImage(imageBook);
        }
        ResultDTO<Book> result = this.bookService.update(book);
        if (result.isError()) {
            this.handleError(req, resp, result, book);
            return;
        }
        resp.sendRedirect(req.getContextPath() + BookEndpointConstant.BOOK_ADMIN_SEARCH_ENDPOINT);
    }

    private void removeBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object id = req.getParameter("id");
        ResultDTO<Book> resultDelete = this.bookService.delete(DataUtil.safeToInt(id));
        if (resultDelete.isError()) {
            req.setAttribute("error", resultDelete.getMessage());
            req.setAttribute("books", this.bookService.search(new BookDTO()));
            req.getRequestDispatcher(PATH_UI_ADMIN_BOOK).forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath()+BookEndpointConstant.BOOK_ADMIN_SEARCH_ENDPOINT);
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp, ResultDTO<Book> result, Book book) throws ServletException, IOException {
        req.setAttribute("error", result.getMessage());
        req.setAttribute("book", book);
        req.setAttribute("categories", this.categoryService.findAll());
        req.getRequestDispatcher(PATH_UI_FORM_BOOK).forward(req, resp);
    }

    private String handleImage(HttpServletRequest req, Book book) throws ServletException, IOException {
        String realPath = req.getServletContext().getRealPath("images/books");

        File realPathAsFile = new File(realPath);
        if (!realPathAsFile.exists()) {
            realPathAsFile.mkdirs();
        }

        Part imageBook = req.getPart("imageBook");
        return this.imageService.store(imageBook, realPath);
    }

    @Override
    public void destroy() {
        this.bookService = null;
        this.categoryService = null;
        this.imageService = null;
    }
}
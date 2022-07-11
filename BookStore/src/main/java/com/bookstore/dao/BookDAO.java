package com.bookstore.dao;

import com.bookstore.constant.StatusBookEnum;
import com.bookstore.dto.BookDTO;
import com.bookstore.entity.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookDAO extends BaseDAO<Book, Integer> {

    public BookDAO() {
        super(Book.class);
    }

    public List<Book> searchBook(BookDTO book) throws Exception {
        StringBuilder searchSql = new StringBuilder("SELECT * FROM book WHERE status = ?");
        List<Object> params = new ArrayList<>();
        params.add(StatusBookEnum.ACTIVE.getStatus());

        if (Objects.nonNull(book.getName())) {
            searchSql.append(" AND name LIKE ?");
            params.add("%"+book.getName()+"%");
        }

        if (Objects.nonNull(book.getIdCategory())) {
            searchSql.append(" AND category_id = ?");
            params.add(book.getIdCategory());
        }

        if (Objects.nonNull(book.getFromPrice())) {
            searchSql.append(" AND price >= ?");
            params.add(book.getFromPrice());
        }

        if (Objects.nonNull(book.getToPrice())) {
            searchSql.append(" AND price <= ?");
            params.add(book.getToPrice());
        }

        return this.jdbcTemplate
                .select(searchSql.toString(), params.toArray())
                .executeQueryList();
    }

    public List<Book> findBookByAuthor(String author) {
        try {
            return this.jdbcTemplate
                    .select(this.generator.selectBy("author"), author)
                    .executeQueryList();
        } catch(Exception ex) {
            return new ArrayList<>();
        }
    }

    public List<Book> findByAuthorElse(String author) {
        try {
            return this.jdbcTemplate
                    .select("SELECT * FROM book WHERE author <> ?", author)
                    .executeQueryList();
        } catch(Exception ex) {
            return new ArrayList<>();
        }
    }

    public int insert(Book book) throws SQLException {
        return this.jdbcTemplate
                .insert(this.generator.insert(), book.getTitle(), book.getName(),  book.getPrice(),book.getAuthor(), book.getQuantity(), book.getCategoryId(), book.getImage(), book.getStatus(), book.getDescription())
                .executeSave();
    }

    public int updateById(Book book) throws SQLException {
        return this.jdbcTemplate
                .update(this.generator.updateById(), book.getTitle(), book.getName(),  book.getPrice(),book.getAuthor(), book.getQuantity(), book.getCategoryId(), book.getImage(), book.getStatus(), book.getDescription(), book.getId())
                .executeSave();
    }
}
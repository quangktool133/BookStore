package com.bookstore.service;

import com.bookstore.constant.AppConstant;
import com.bookstore.dao.UserDAO;
import com.bookstore.dto.ResultDTO;
import com.bookstore.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.Objects;

public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public ResultDTO<User> login(User user) {
        try {
            logger.info("log in with username: {}", user.getUsername());
            User foundUser = this.userDAO.findByUsername(user.getUsername());
            if (Objects.isNull(foundUser)) {
                logger.error("username is incorrect");
                return new ResultDTO<>(null, true, "Tài khoản không chính xác");
            }

            if (!foundUser.getPassword().equals(user.getPassword())) {
                logger.error("password is incorrect");
                return new ResultDTO<>(null, true, "Mật khẩu không chính xác");
            }
            return new ResultDTO<>(foundUser, false, "");
        } catch(Exception ex) {
            return new ResultDTO<>(null, true, "Đăng nhập thất bại");
        }
    }

    public User getCurrentUser(HttpSession httpSession) {
        Object user = httpSession.getAttribute(AppConstant.SESSION_USER);
        if (Objects.isNull(user)) {
            return null;
        }

        if (!(user instanceof User)) return null;
        return (User) user;
    }
}
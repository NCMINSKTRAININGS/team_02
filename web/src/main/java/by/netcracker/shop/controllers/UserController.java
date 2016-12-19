package by.netcracker.shop.controllers;

import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.exceptions.UtilException;
import by.netcracker.shop.pojo.User;
import by.netcracker.shop.services.UserService;
import by.netcracker.shop.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String showLoginForm(HttpServletRequest request) {
        User user;
        HttpSession session = request.getSession();
        user = getUserFromSession(session);
        if (user == null)
            return "user/login";
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    protected String loginUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String salt;
        User user;
        if (!(username.isEmpty() || password.isEmpty() ||
                username.length() > 45 || password.length() > 45)) {
            try {
                salt = PasswordGenerator.getInstance().generateSalt(password);
                password = PasswordGenerator.getInstance().generatePassword(password, salt);
                user = service.getByUsernamePasswordSalt(username, password, salt);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                    return "redirect:/";
                }
            } catch (ServiceException | UtilException e) {
                //todo
            }
        }
        request.setAttribute("errorLogin", "WRONG_LOGIN");
        return "user/login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(HttpServletRequest request){
        User user;
        HttpSession session = request.getSession();
        user = getUserFromSession(session);
        if (user == null)
            return "user/registration";
        session.setAttribute("user", user);
        return "redirect:/";
    }

    private User getUserFromSession(HttpSession session) {
        User user;
        user = (User)session.getAttribute("user");
        if (user != null && user.getId() != null) {
            try {
                user = service.getById(user.getId());
            } catch (ServiceException e) {
                //todo
                return null;
            }
            return user;
        }
        return null;
    }
}

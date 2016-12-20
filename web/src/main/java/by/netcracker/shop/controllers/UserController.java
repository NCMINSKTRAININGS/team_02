package by.netcracker.shop.controllers;

import by.netcracker.shop.constants.Parameters;
import by.netcracker.shop.enums.UserRole;
import by.netcracker.shop.enums.UserStatus;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.exceptions.UtilException;
import by.netcracker.shop.pojo.User;
import by.netcracker.shop.services.UserService;
import by.netcracker.shop.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(Parameters.CONTROLLER_USER)
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping(value = Parameters.REQUEST_USER_LOGIN, method = RequestMethod.GET)
    public String showLoginForm(ModelMap model, HttpServletRequest request) {
                User user;
        HttpSession session = request.getSession();
        user = getUserFromSession(session);
        if (user == null) {
            model.addAttribute(Parameters.FIELD_USER, new User());
            return Parameters.TILES_LOGIN;
        }
        session.setAttribute(Parameters.ENTITY_USER, user);
        return "redirect:" + Parameters.CONTROLLER_INDEX;
    }

    @RequestMapping(value = Parameters.REQUEST_USER_LOGIN, method = RequestMethod.POST)
    public String loginUser(@Valid @ModelAttribute(Parameters.FIELD_USER) User user, BindingResult result,
                                 ModelMap model, HttpServletRequest request) {
        String password;
        String salt;
        if (!result.hasErrors()) {
            try {
                salt = PasswordGenerator.getInstance().generateSalt(user.getPassword());
                password = PasswordGenerator.getInstance().generatePassword(user.getPassword(), salt);
                user = service.getByUsernamePasswordSalt(user.getUsername(), password, salt);
            } catch (ServiceException | UtilException e) {
                //todo
            }
            if (user != null) {
                request.getSession().setAttribute(Parameters.ENTITY_USER, user);
                return "redirect:" + Parameters.CONTROLLER_INDEX;
            }
        }
        model.addAttribute(Parameters.FIELD_ERROR_MESSAGE, Parameters.MESSAGE_LOGIN_ERROR);
        return Parameters.TILES_LOGIN;
    }

    @RequestMapping(value = Parameters.REQUEST_USER_REGISTRATION, method = RequestMethod.GET)
    public String showRegistrationForm(HttpServletRequest request){
        User user;
        HttpSession session = request.getSession();
        user = getUserFromSession(session);
        if (user == null)
            return Parameters.TILES_REGISTRATION;
        session.setAttribute(Parameters.ENTITY_USER, user);
        return "redirect:" + Parameters.CONTROLLER_INDEX;
    }

    @RequestMapping(value = Parameters.REQUEST_USER_REGISTRATION, method = RequestMethod.POST)
    protected String registrationUser(HttpServletRequest request) {
        String firstname = request.getParameter(Parameters.FIELD_FIRST_NAME);
        String lastname = request.getParameter(Parameters.FIELD_LAST_NAME);
        String username = request.getParameter(Parameters.FIELD_USERNAME);
        String password = request.getParameter(Parameters.FIELD_PASSWORD);
        String salt;
        User user;
        Long id;
        if (!(username.isEmpty() || password.isEmpty() || username.length() > 45 || password.length() > 45 ||
                firstname.isEmpty() || lastname.isEmpty() || firstname.length() > 45 || lastname.length() > 45)) {
            try {
                if (service.getByUsername(username) != null) {
                    request.setAttribute(Parameters.FIELD_ERROR_MESSAGE,
                            Parameters.MESSAGE_USERNAME_EXIST_ERROR);
                    return Parameters.TILES_REGISTRATION;
                }
                salt = PasswordGenerator.getInstance().generateSalt(password);
                password = PasswordGenerator.getInstance().generatePassword(password, salt);
                user = new User();
                user.setFirstName(firstname);
                user.setLastName(lastname);
                user.setUsername(username);
                user.setPassword(password);
                user.setSalt(salt);
                user.setRole(UserRole.CLIENT);
                user.setStatus(UserStatus.ONLINE);
                id = service.insert(user);
                user.setId(id);
                request.getSession().setAttribute(Parameters.ENTITY_USER, user);
                return "redirect:" + Parameters.CONTROLLER_INDEX;
            } catch (ServiceException | UtilException e) {
                //todo
            }
        }
        request.setAttribute(Parameters.FIELD_ERROR_MESSAGE, Parameters.MESSAGE_WRONG_DATA_ERROR);
        return Parameters.TILES_REGISTRATION;
    }

    private User getUserFromSession(HttpSession session) {
        User user;
        user = (User)session.getAttribute(Parameters.ENTITY_USER);
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

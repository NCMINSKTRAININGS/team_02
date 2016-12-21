package by.netcracker.shop.controllers;

import by.netcracker.shop.constants.Parameters;
import by.netcracker.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(Parameters.CONTROLLER_USER)
public class UserController {
    @Autowired
    UserService service;
/*
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
               // user = service.getByUsernamePasswordSalt(user.getUsername(), password, salt);
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
    public String showRegistrationForm(ModelMap model, HttpServletRequest request){
        User user;
        HttpSession session = request.getSession();
        user = getUserFromSession(session);
        if (user == null) {
            model.addAttribute(Parameters.FIELD_USER, new User());
            return Parameters.TILES_REGISTRATION;
        }
        session.setAttribute(Parameters.ENTITY_USER, user);
        return "redirect:" + Parameters.CONTROLLER_INDEX;
    }

    @RequestMapping(value = Parameters.REQUEST_USER_REGISTRATION, method = RequestMethod.POST)
    protected String registrationUser(@Valid @ModelAttribute(Parameters.FIELD_USER) User user, ModelMap model,
                                      HttpServletRequest request, BindingResult result) {
        String password;
        String salt;
        Long id;
        if (!result.hasErrors()) {
            try {
                if (service.getByUsername(user.getUsername()) != null) {
                    request.setAttribute(Parameters.FIELD_ERROR_MESSAGE,
                            Parameters.MESSAGE_USERNAME_EXIST_ERROR);
                    return Parameters.TILES_REGISTRATION;
                }
                salt = PasswordGenerator.getInstance().generateSalt(user.getPassword());
                password = PasswordGenerator.getInstance().generatePassword(user.getPassword(), salt);
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

    public User getUserFromSession(HttpSession session) {
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
    */

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "user/login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}

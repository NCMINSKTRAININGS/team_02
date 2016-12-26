package by.netcracker.shop.controllers;

import by.netcracker.shop.constants.Parameters;
import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.enums.UserRole;
import by.netcracker.shop.enums.UserStatus;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping(Parameters.CONTROLLER_USER)
public class UserController {
    @Autowired
    UserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = Parameters.REQUEST_USER_REGISTRATION, method = RequestMethod.GET)
    public String showRegistrationForm(ModelMap model){
        model.addAttribute(Parameters.FIELD_USER, new UserDTO());
        return Parameters.TILES_REGISTRATION;
    }

    @RequestMapping(value = Parameters.REQUEST_USER_REGISTRATION, method = RequestMethod.POST)
    protected String registrationUser(@Valid @ModelAttribute(Parameters.FIELD_USER) UserDTO user,
                                      BindingResult result,
                                      ModelMap model) {
        if (!result.hasErrors()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(UserRole.CLIENT);
            user.setStatus(UserStatus.ONLINE);
            try {
                service.insert(user);
                return "redirect:" + Parameters.CONTROLLER_INDEX;
            } catch (ServiceException e) {
                //todo
            }
        } else {
            model.addAttribute(Parameters.FIELD_ERROR_MESSAGE, Parameters.MESSAGE_WRONG_DATA_ERROR);
        }
        return Parameters.TILES_REGISTRATION;
    }

    @RequestMapping(value = Parameters.REQUEST_USER_LOGIN, method = RequestMethod.GET)
    public String loginPage(ModelMap model,
                            @RequestParam(value = Parameters.PARAMETER_ERROR, required = false) String error,
                            @RequestParam(value = Parameters.PARAMETER_LOGOUT, required = false) String logout) {
        if (error != null)
            model.addAttribute(Parameters.FIELD_ERROR_MESSAGE, Parameters.MESSAGE_LOGIN_ERROR);
        if (logout != null)
            model.addAttribute(Parameters.FIELD_MESSAGE, Parameters.MESSAGE_LOGOUT);
        return Parameters.TILES_LOGIN;
    }

    @RequestMapping(value = Parameters.REQUEST_USER_LOGOUT, method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:" +
                Parameters.CONTROLLER_USER +
                Parameters.REQUEST_USER_LOGIN +
                "?" +
                Parameters.PARAMETER_LOGOUT;
    }
}

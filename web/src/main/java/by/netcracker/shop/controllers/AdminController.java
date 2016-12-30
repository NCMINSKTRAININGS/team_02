package by.netcracker.shop.controllers;

import by.netcracker.shop.constants.Parameters;
import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(Parameters.CONTROLLER_ADMIN)
public class AdminController {
    @Autowired
    UserService userService;

    @RequestMapping(value = Parameters.REQUEST_USERS, method = RequestMethod.GET)
    public String listUsers(ModelMap modelMap) {
        List<UserDTO> users = null;
        try {
            users = userService.getAll();
        } catch (ServiceException e) {
            //todo
        }
        modelMap.addAttribute(Parameters.FIELD_USERS, users);
        return Parameters.TILES_USERS;
    }

    @RequestMapping(value = Parameters.REQUEST_USER_EDIT, method = RequestMethod.GET)
    public String editUser(@PathVariable Long id, ModelMap modelMap) {
        UserDTO dto = null;
        try {
            dto = userService.getById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (dto != null) {
            dto.setPassword("**********");
        }
        modelMap.addAttribute(Parameters.FIELD_USER_DTO, dto);
        return Parameters.TILES_USER_EDIT;
    }

    @RequestMapping(value = Parameters.REQUEST_USER_EDIT, method = RequestMethod.POST)
    public String updateUser(@Valid @ModelAttribute(Parameters.FIELD_USER_DTO) UserDTO dto,
                                BindingResult bindingResult, @PathVariable Long id, ModelMap modelMap) {
        UserDTO oldDTO;
        if (bindingResult.hasErrors()) {
            return Parameters.TILES_USER_EDIT;
        }
        try {
            oldDTO = userService.getById(id);
            oldDTO.setStatus(dto.getStatus());
            oldDTO.setRole(dto.getRole());
            userService.insert(oldDTO);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:" + Parameters.CONTROLLER_ADMIN + Parameters.REQUEST_USERS;
    }

}

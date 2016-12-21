package by.netcracker.shop.services.impl;

import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.enums.UserRole;
import by.netcracker.shop.enums.UserStatus;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = null;
        try {
            user = userService.getByUsername(username);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (user==null){
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User
                (user.getUsername(),user.getPassword(),
                        user.getStatus().equals(UserStatus.ONLINE)||equals(UserStatus.OFLINE),true,true,true,getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserDTO user){
        List<GrantedAuthority> authorities = new ArrayList<>();

            authorities.add(new SimpleGrantedAuthority("ROLE_" + UserRole.valueOf(String.valueOf(user.getRole()))));

            return authorities;
    }
}

package com.gaf.reminderserver.services;


import com.gaf.reminderserver.dao.UserDao;
import com.gaf.reminderserver.entities.User;
import com.gaf.reminderserver.models.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        User user = userDao.findByEmailIgnoreCase(username).get();
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }
}
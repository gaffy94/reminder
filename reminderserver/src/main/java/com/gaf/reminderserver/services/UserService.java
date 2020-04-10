package com.gaf.reminderserver.services;

import com.gaf.reminderserver.dao.UserDao;
import com.gaf.reminderserver.entities.User;
import com.gaf.reminderserver.models.Login;
import com.gaf.reminderserver.models.Response;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    HashUtil hashUtil;
    public Response create(User user) {
        Response resp = new Response();
        try {

            if(user.getId() != null){
                Optional<User> byId = userDao.findById(user.getId());
                byId.get().setEmail(user.getEmail());
                byId.get().setFirstName(user.getFirstName());
                byId.get().setLastName(user.getLastName());
                byId.get().setPhoneNumber(user.getPhoneNumber());
                byId.get().setAprtmntNumber(user.getAprtmntNumber());
                if(user.getPassword() != null){
                    byId.get().setPassword(hashUtil.doHash(user.getPassword()));
                }
                user = byId.get();
            } else {
                user.setPassword(hashUtil.doHash(user.getPassword()));
            }
            User save = userDao.save(user);
            resp.setResponseMessage("SUCCESS");
            resp.setResponseCode("000");
            resp.setData(save);
        }catch (Exception e){
            e.printStackTrace();
            resp.setResponseCode("999");
            resp.setResponseMessage("Failed");
        }
        return resp;
    }

    public boolean doAuth(Login login) throws NoSuchAlgorithmException {
        boolean isAuth=false;
        System.out.println("login user : " +login.getUsername());
        Optional<User> byEmailIgnoreCase = userDao.findByEmailIgnoreCase(login.getUsername());
        if(byEmailIgnoreCase.isPresent()){
            if(byEmailIgnoreCase.get().getPassword().equalsIgnoreCase(hashUtil.doHash(login.getPassword()))){
                isAuth = true;
            }
        }
        return isAuth;
    }
}

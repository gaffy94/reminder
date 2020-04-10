package com.gaf.reminderserver.controllers;

import com.gaf.reminderserver.entities.User;
import com.gaf.reminderserver.models.Response;
import com.gaf.reminderserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/create")
    public Response create(@RequestBody User user) {
        return userService.create(user);
    }
}

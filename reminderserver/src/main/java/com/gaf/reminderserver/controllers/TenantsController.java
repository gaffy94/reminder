package com.gaf.reminderserver.controllers;

import com.gaf.reminderserver.entities.Tenants;
import com.gaf.reminderserver.entities.User;
import com.gaf.reminderserver.models.Response;
import com.gaf.reminderserver.services.TenantsService;
import com.gaf.reminderserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantsController {
    @Autowired
    TenantsService tenantsService;
    @PostMapping("/create")
    public Response create(@RequestBody Tenants tenants) {
        return tenantsService.create(tenants);
    }
    @GetMapping("/fetchall")
    public List<Tenants> fetchAll(){
        return tenantsService.fetchAll();
    }

    @GetMapping("/togglepaid/{id}")
    public Response markPaid(@PathVariable("id") Long id){
        return tenantsService.togglePaid(id);
    }

    @GetMapping("/reset")
    public Response reset(){
        return tenantsService.reset();
    }
}

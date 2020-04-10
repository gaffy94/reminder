package com.gaf.reminderserver.services;

import com.gaf.reminderserver.dao.TenantsDao;
import com.gaf.reminderserver.entities.Tenants;
import com.gaf.reminderserver.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class TenantsService {
    @Autowired
    TenantsDao tenantsDao;
@Autowired
    Environment env;
    @Autowired
    NotifyService notifyService;

    public Response create(Tenants tenants) {
        Response resp = new Response();
        try {

            if(tenants.getId() != null){
                Optional<Tenants> byId = tenantsDao.findById(tenants.getId());
                byId.get().setEmail(tenants.getEmail());
                byId.get().setFirstName(tenants.getFirstName());
                byId.get().setLastName(tenants.getLastName());
                byId.get().setPhoneNumber(tenants.getPhoneNumber());
                byId.get().setAprtmntNumber(tenants.getAprtmntNumber());
                tenants = byId.get();
            }
            Tenants save = tenantsDao.save(tenants);
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

    public List<Tenants> fetchAll() {
    return tenantsDao.findAll();
    }

    public Response togglePaid(Long id) {
        Response resp = new Response();
        try {
            Optional<Tenants> byId = tenantsDao.findById(id);
            byId.get().setPaid(!byId.get().getPaid());
            Tenants save = tenantsDao.save(byId.get());
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
@Transactional
@Modifying
    public Response reset() {
    Response resp = new Response();
    try {
        tenantsDao.doUpdate();
        resp.setResponseMessage("SUCCESS");
        resp.setResponseCode("000");
    }catch (Exception e){
        e.printStackTrace();
        resp.setResponseCode("999");
        resp.setResponseMessage("Failed");
    }
    return resp;
    }

    public void sendReminders() {
        try{
            // check if time is right to run
            Calendar cal = Calendar.getInstance();
            String[] dayofmonths = env.getProperty("dayofmonths").split(",");
            List<String> lInt = Arrays.asList(dayofmonths);
            AtomicBoolean isAllowed = new AtomicBoolean(false);
            lInt.parallelStream().forEach((mf)->{
                if(mf.equals(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)))){
                    isAllowed.set(true);
                }
            });
            System.out.println("isAllowed : "+isAllowed.get());
            if(isAllowed.get()){
                // fetch all tenants where paid false
                List<Tenants> defaulters = tenantsDao.findByIsPaid(false);
                defaulters.parallelStream().forEach((df -> {
                    // send email and sms to them
                    notifyService.doNotify(df);
                }));
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        checkToResetAll();
    }

    public void checkToResetAll(){
        if("7".equals(String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)))){
          reset();
        }
    }
}

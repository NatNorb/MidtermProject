package com.ironhack.MidtermProject.service.impl;

import com.ironhack.MidtermProject.dao.users.Admin;
import com.ironhack.MidtermProject.dao.users.ThirdParty;
import com.ironhack.MidtermProject.repository.users.AdminRepository;
import com.ironhack.MidtermProject.service.interfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AdminService implements IAdminService{

    @Autowired
    AdminRepository adminRepository;

    public Admin createAdmin(Admin admin){
        Admin newAdmin = new Admin(admin.getName());
        return adminRepository.save(newAdmin);
    }

}

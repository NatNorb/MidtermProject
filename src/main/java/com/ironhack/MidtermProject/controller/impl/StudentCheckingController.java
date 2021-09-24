package com.ironhack.MidtermProject.controller.impl;

import com.ironhack.MidtermProject.controller.interfaces.IStudentCheckingController;
import com.ironhack.MidtermProject.dao.accounts.Account;
import com.ironhack.MidtermProject.dao.accounts.Checking;
import com.ironhack.MidtermProject.dao.accounts.StudentChecking;
import com.ironhack.MidtermProject.repository.accounts.StudentCheckingRepository;
import com.ironhack.MidtermProject.service.interfaces.IStudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class StudentCheckingController implements IStudentCheckingController {

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @Autowired
    private IStudentCheckingService studentCheckingService;

    @GetMapping("/student")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentChecking> searchStudentChecking() {
        return studentCheckingRepository.findAll();
    }


    //Admins should be able to access the balance for any account and to modify it.
    @PatchMapping("/student/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyCheckingBalance(@PathVariable Long id, @RequestBody @Valid StudentChecking studentChecking) {
        studentCheckingService.modifyStudentCheckingBalance(id, studentChecking);

    }
}

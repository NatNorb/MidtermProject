package com.ironhack.MidtermProject.controller.impl;

import com.ironhack.MidtermProject.controller.interfaces.IStudentCheckingController;
import com.ironhack.MidtermProject.repository.accounts.StudentCheckingRepository;
import com.ironhack.MidtermProject.service.interfaces.IStudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentCheckingController implements IStudentCheckingController {

    @Autowired
    private StudentCheckingRepository studentRepository;

    @Autowired
    private IStudentCheckingService studentCheckingService;

}

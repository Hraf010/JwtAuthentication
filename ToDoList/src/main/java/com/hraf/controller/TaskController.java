package com.hraf.controller;


import com.hraf.dao.AppUserRepository;
import com.hraf.dao.TaskRepository;
import com.hraf.entities.AppUser;
import com.hraf.entities.Task;
import com.hraf.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AccountService accountService;

    @GetMapping("/tasks")
    public List<Task> getTasks(){
        return taskRepository.findAll();
    }
    @PostMapping("/tasks")
    public void addTask(@RequestBody Task task){
        taskRepository.save(task);
    }

    @PostMapping("/register")
    public AppUser register(@RequestBody RegisterForm userForm){
        if(!userForm.getPassword().equals(userForm.getRepassword()))
            throw new RuntimeException("You must Confirm your password");
        AppUser user = accountService.findUserByUsername(userForm.getUserName());
        if(user !=null)
            throw new RuntimeException("username already exists" + userForm.getUserName());
        AppUser appUser = new AppUser(userForm.getUserName(),userForm.getPassword());
        accountService.addRoleToUser(appUser.getUsername(),"USER");
        return accountService.saveUser(appUser);
    }

}

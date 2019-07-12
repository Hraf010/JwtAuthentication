package com.hraf;

import com.hraf.controller.TaskController;
import com.hraf.entities.AppRole;
import com.hraf.entities.AppUser;
import com.hraf.entities.Task;
import com.hraf.service.AccountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args)

    {
      ApplicationContext context =  SpringApplication.run(DemoApplication.class, args);
//        TaskController taskController = context.getBean(TaskController.class);
//        taskController.addTask(new Task("Task 1 "));
//        taskController.addTask(new Task("Task 2 "));
//        taskController.addTask(new Task("Task 3 "));
//
//        for (Task t : taskController.getTasks()){
//            System.out.println(t.getName());
//        }
//        AccountService accountService = context.getBean(AccountService.class);
//        accountService.saveUser(new AppUser("admin","1234"));
//        accountService.saveUser(new AppUser("user","1234"));
//
//        accountService.saveRole(new AppRole("ADMIN"));
//        accountService.saveRole(new AppRole("USER"));
//
//        accountService.addRoleToUser("admin" ,"ADMIN");
//        accountService.addRoleToUser("admin" ,"USER");
//        accountService.addRoleToUser("user" ,"USER");




    }

    @Bean
    public BCryptPasswordEncoder getBPE(){
        return  new BCryptPasswordEncoder();
    }



}

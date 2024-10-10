package org.example.config.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.user.entity.User;
import org.example.user.service.UserService;

import java.time.LocalDate;
import java.util.UUID;

@WebListener
public class DataInitialization implements ServletContextListener {
    private UserService userService;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        userService = (UserService) event.getServletContext().getAttribute("userService");
        init();
    }
    private void init(){
        User jurek = User.builder().id(UUID.randomUUID()).email("example@org").username("jurek").registrationDate(LocalDate.now()).build();
        User marek = User.builder().id(UUID.randomUUID()).email("example@pl").username("marek").registrationDate(LocalDate.now()).build();
        User krzysztof = User.builder().id(UUID.randomUUID()).email("example@com").username("krzysztof").registrationDate(LocalDate.now()).build();
        User franek = User.builder().id(UUID.randomUUID()).email("org@example").username("franek").registrationDate(LocalDate.now()).build();

        userService.createUser(jurek);
        userService.createUser(marek);
        userService.createUser(krzysztof);
        userService.createUser(franek);
    }
}

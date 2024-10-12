package org.example.config.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;
import org.example.user.entity.User;
import org.example.user.service.UserService;

import java.io.InputStream;
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

    private void init() {
        User jurek = User.builder()
                .id(UUID.fromString("525d3e7b-bb1f-4c13-bf17-926d1a12e4c0"))
                .email("example@org")
                .username("jurek")
                .registrationDate(LocalDate.now())
                .build();

        User marek = User.builder()
                .id(UUID.fromString("cc0b0577-bb6f-45b7-81d6-3db88e6ac19f"))
                .email("example@pl")
                .username("marek")
                .registrationDate(LocalDate.now())
                .build();
        User krzysztof = User.builder()
                .id(UUID.fromString("f08ef7e3-7f2a-4378-b1fb-2922d730c70d"))
                .email("example@com")
                .username("krzysztof")
                .registrationDate(LocalDate.now())
                .build();
        User franek = User.builder()
                .id(UUID.fromString("ff327e8a-77c0-4f9b-90a2-89e16895d1e1"))
                .email("org@example")
                .username("franek")
                .registrationDate(LocalDate.now())
                .build();

        userService.createUser(jurek);
        userService.createUser(marek);
        userService.createUser(krzysztof);
        userService.createUser(franek);
    }
}

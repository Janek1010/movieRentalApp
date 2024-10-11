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
                .avatar(getResourceAsByteArray("../avatar/person1.png"))
                .build();

        User marek = User.builder()
                .id(UUID.randomUUID())
                .email("example@pl")
                .username("marek")
                .registrationDate(LocalDate.now())
                .avatar(getResourceAsByteArray("../avatar/person2.png"))
                .build();
        User krzysztof = User.builder()
                .id(UUID.randomUUID())
                .email("example@com")
                .username("krzysztof")
                .registrationDate(LocalDate.now())
                .avatar(getResourceAsByteArray("../avatar/person3.png"))
                .build();
        User franek = User.builder()
                .id(UUID.randomUUID())
                .email("org@example")
                .username("franek")
                .registrationDate(LocalDate.now())
                .avatar(getResourceAsByteArray("../avatar/person4.png"))
                .build();

        userService.createUser(jurek);
        userService.createUser(marek);
        userService.createUser(krzysztof);
        userService.createUser(franek);
    }
    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            if (is != null) {
                return is.readAllBytes();
            } else {
                throw new IllegalStateException("Unable to get resource %s".formatted(name));
            }
        }
    }
}

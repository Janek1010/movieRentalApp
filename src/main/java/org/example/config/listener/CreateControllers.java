package org.example.config.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.factories.DtoFunctionFactory;
import org.example.user.controller.impl.UserControllerImpl;
import org.example.user.service.UserService;

@WebListener
public class CreateControllers implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        UserService userService = (UserService) event.getServletContext().getAttribute("userService");
        event.getServletContext().setAttribute("userController", new UserControllerImpl(
                userService,
                new DtoFunctionFactory()
        ));
    }
}

package org.example.controller.servlet;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.example.user.controller.api.UserController;

import java.util.regex.Pattern;

@WebServlet(
        urlPatterns = {
                ApiServlet.Paths.API +"/*"
        }
)
public class ApiServlet extends HttpServlet {
    private UserController userController;

    public static final class Paths {
        public static final String API = "/api";
    }
    public static final class Patterns {
        private static final Pattern UUID = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");
        public static final Pattern USER = Pattern.compile("/users/(%s)".formatted(UUID.pattern()));
        public static final Pattern USERS = Pattern.compile("/users/?");
    }

    private final Jsonb jsonb = JsonbBuilder.create();

    // dokonczyc ten servlet i jeszcze ta obsluga bledow i testowac wtedy
}

package org.example.config.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.Util.CloningUtility;
import org.example.database.DataBase;

@WebListener
public class CreateDataBase  implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute("datasource",new DataBase(new CloningUtility()));
    }
}

package J.AppUsers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import J.AppUsers.model.AdminState;
import J.AppUsers.model.App;
import J.AppUsers.model.User;

@Configuration
public class ServiceConfig {
    @Bean
    public List<App> applist() {
        return new ArrayList<App>();
    }
    @Bean
    public List<User> userlist() {
        return new ArrayList<User>();
    }
    @Bean
    public AdminState admin() {
        return new AdminState();
    }
}

package J.AppUsers.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/users").setViewName("users");
        registry.addViewController("/apps").setViewName("apps");
        registry.addViewController("/stats").setViewName("stats");
        registry.addViewController("/addapp").setViewName("addapp");
        registry.addViewController("/editapp").setViewName("editapp");
        registry.addViewController("/edituser").setViewName("edituser");
        registry.addViewController("/adminlogin").setViewName("adminlogin");
    }
}

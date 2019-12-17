package com.concretepage;

import javax.servlet.http.HttpServlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.concretepage.servlets.WitajMordoServlet;;

@Configuration
public class WebConfig {
   @Bean
   public ServletRegistrationBean<HttpServlet> countryServlet() {
	   ServletRegistrationBean<HttpServlet> servRegBean = new ServletRegistrationBean<>();
	   servRegBean.setServlet(new WitajMordoServlet());
	   servRegBean.addUrlMappings("/getpost/*");
	   servRegBean.setLoadOnStartup(1);
	   return servRegBean;
   }
}

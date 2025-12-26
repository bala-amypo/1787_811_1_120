package com.example.demo;

import com.example.demo.servlet.SimpleStatusServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.demo.entity")
@EnableJpaRepositories("com.example.demo.repository")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean<SimpleStatusServlet> statusServlet() {
        ServletRegistrationBean<SimpleStatusServlet> servletReg = 
            new ServletRegistrationBean<>(new SimpleStatusServlet(), "/health");
        servletReg.setLoadOnStartup(1);
        return servletReg;
    }
}

package com.smu.rest.library;

import com.smu.rest.library.api.controllers.filters.ApiFilter;
import com.smu.rest.library.models.Author;
import com.smu.rest.library.repositories.AuthorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class LibraryApplication {

    public static void main(String[] args) {
       SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<ApiFilter> loggingFilter(){
        FilterRegistrationBean<ApiFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new ApiFilter());
        registrationBean.addUrlPatterns("/books/v1/*", "/authors/v1/*");

        return registrationBean;
    }

}

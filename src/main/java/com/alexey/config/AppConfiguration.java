package com.alexey.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan({"com.alexey.config",
                "com.alexey.controller",
                "com.alexey.dao",
                "com.alexey.service"})
@Import(JdbcConfiguration.class)
//aaaaaaaaaaaaaaaa
public class AppConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public MappingJackson2HttpMessageConverter jsonConverter(){
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        jsonConverter.setObjectMapper(objectMapper);

        return jsonConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        converters.add(jsonConverter());
        super.configureMessageConverters(converters);
    }

}

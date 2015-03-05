package de.codepitbull.hystrix.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jmader on 27.02.15.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "de.codepitbull.hystrix.component")
public class WebApplicationContext extends WebMvcConfigurerAdapter{


}

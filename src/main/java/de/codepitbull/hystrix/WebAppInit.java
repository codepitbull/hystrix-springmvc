package de.codepitbull.hystrix;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.netflix.turbine.init.TurbineInit;
import com.netflix.turbine.streaming.servlet.TurbineStreamServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by jmader on 27.02.15.
 */
public class WebAppInit implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = getContext();

        ServletRegistration.Dynamic turbineStreamServlet = servletContext.addServlet("TurbineStreamServlet", TurbineStreamServlet.class.getName());
        turbineStreamServlet.setLoadOnStartup(1);
        turbineStreamServlet.addMapping("/turbine.stream");

        ServletRegistration.Dynamic hystrixServlet = servletContext.addServlet("HystrixMetricsStreamServlet", HystrixMetricsStreamServlet.class.getName());
        hystrixServlet.setLoadOnStartup(1);
        hystrixServlet.addMapping("/hystrix.stream");

        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/spring/*");

        TurbineInit.init();
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("de.codepitbull.hystrix.spring");
        return context;
    }
}

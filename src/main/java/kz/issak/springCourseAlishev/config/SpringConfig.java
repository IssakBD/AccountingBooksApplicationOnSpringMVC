package kz.issak.springCourseAlishev.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@ComponentScan("kz.issak.springCourseAlishev")
@EnableWebMvc //Spring MVC приложение которое поддерживает web функции = <mvc:annotation-driven/> который прописал в applicationContextMVC.xml
public class SpringConfig implements WebMvcConfigurer { //данный интерфейс реализуется тогда когда мы хотим под себя настроить SpringMVC, в данном случае мы хотим использовать вместо стандартного шаблонизатора - шаблонизатор thymeleaf.
    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext) { //ApplicationContext внедряется самим Spring
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext); //ApplicationContext используется здесь чтобы настроить наш thymeleaf
        templateResolver.setPrefix("/WEB-INF/views/"); //путь до представлении (views)
        templateResolver.setSuffix(".html"); // расширение представлении
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() { //конфигурация представлении
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) { //реализуем метод интерфейса в котором мы задаем шаблонизатор, в данном случае thymeleaf.
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

}
//Этот класс полностью эквивалентен ApplicationContextMVC.xml, ApplicationContextMVC.xml - можно удалить, а я его закоментирую.
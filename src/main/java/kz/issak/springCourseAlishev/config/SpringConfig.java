package kz.issak.springCourseAlishev.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan("kz.issak.springCourseAlishev")
@EnableWebMvc //Spring MVC приложение которое поддерживает web функции = <mvc:annotation-driven/> который прописал в applicationContextMVC.xml
@PropertySource("classpath:database.properties") //Импортируем внешний файл с паролями и данными для подключения к БД
public class SpringConfig implements WebMvcConfigurer { //данный интерфейс реализуется тогда когда мы хотим под себя настроить SpringMVC, в данном случае мы хотим использовать вместо стандартного шаблонизатора - шаблонизатор thymeleaf.
    private final ApplicationContext applicationContext;
    private final Environment environment; //С помощью Environment мы можем получить доступ к свойствам которые подгрузились на наше приложение

    @Autowired
    public SpringConfig(ApplicationContext applicationContext, Environment environment) { //ApplicationContext внедряется самим Spring
        this.applicationContext = applicationContext;
        this.environment = environment;
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

    //Реализовано для JDBC Template, чтобы не дублировать код для разных таблиц в разные ДАО классы, мы выносим все данные для подключения к БД в отдельный объект который будет создан спрингом.
    @Bean
    public DataSource dataSource(){ //указываем JDBC к какой базе подключатся (реализуется для JDBC Template)
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("driver")));
        dataSource.setUrl(environment.getProperty("url"));
        dataSource.setUsername(environment.getProperty("login"));
        dataSource.setPassword(environment.getProperty("password"));

        return dataSource;
    }

    //Будет создан объект/бин JDBC Template в который положим данные для подключения в БД
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

}
//Этот класс полностью эквивалентен ApplicationContextMVC.xml, ApplicationContextMVC.xml - можно удалить, а я его закоментирую.
package kz.issak.springCourseAlishev.config;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

//Можно наследоваться от interface WebApplicationInitializer где нужно будет писать больше кода но и будет больше контроля, но мы используем более простой способ где мы будем наследоваться от асбтрактного класса AbstractAnnotationConfigDispatcherServletInitializer (который сам реализует интерфейс WebApplicationInitializer) в котором уже многие методы реализованы за нас. Остается дописать лишь мелочи.
public class SpringMVCDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

//    <dependency>
//      <groupId>javax.servlet</groupId>
//      <artifactId>javax.servlet-api</artifactId>
//      <version>4.0.1</version>
//      <scope>provided</scope>
//    </dependency> нужно добавить зависимость javax-api так как она используется AbstractAnnotationConfigDispatcherServletInitializer абстрактным классом.

    @Override
    protected Class<?>[] getRootConfigClasses() { //данный метод не будет использоваться в нашем приложении
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class}; //Теперь наш класс который исполняет роль web.xml знает где лежит Spring Configuration
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"}; //Это как и в web.xml, указываем серверу, что любые запросы от клиента скидывать на контроллер
    }

    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException { //Фильтр который принимает POST запросом от HTML меняет его метод на PATCH,DELETE,PUT который будет прописан в thymeleaf.
        super.onStartup(aServletContext); //когда наше приложение стартует мы приминяем к нему метод который описан ниже
        registerHiddenFieldFilter(aServletContext);
    }

    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
        //Фильтр называется hiddenHttpMethodFilter, мы не реализуем фильтр он уже есть в Spring мы просто добавляем его к нашему приложению.
        //Этот фильтр и смотрит на значение скрытого поля _method и будет перенаправлять на нужный метод контроллера.
        //В springboot данный фильтр будет занимать всего 1 строку в конфигурационном файле
    }
}
//Этот класс полностью эквивалентен web.xml, web.xml - можно удалить, а я его закоментирую.
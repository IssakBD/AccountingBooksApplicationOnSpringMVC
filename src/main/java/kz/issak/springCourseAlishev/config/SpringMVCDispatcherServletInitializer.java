package kz.issak.springCourseAlishev.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

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
}

//Этот класс полностью эквивалентен web.xml, web.xml - можно удалить, а я его закоментирую.
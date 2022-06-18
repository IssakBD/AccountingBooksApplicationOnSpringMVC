# AccountingBooksApplicationOnSpringMVC

Чтобы приложение работало нужно удалить web.xml и ApplicationContextMVC.xml по пути src/main/webapp/WEB-INF/ так как я реализовал конфиг классы SpringMVCDispatcherServletInitializer, SpringConfig которые эквивалетны этим xml конфигурациям. В репозитории не удалял xml так как в комментах объяснил какой тэг для чего используется. Если приложение запустить в таком виде будут конфликты между xml и java class конфигурациями. УДАЛЯЙТЕ xml конфигурации чтобы запустить приложение.

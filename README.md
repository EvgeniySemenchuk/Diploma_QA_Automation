# Дипломный проект - автоматизация тестирования для wildberries.by
Данный проект содержит реализиацию фреймоворка для тестирования wildberries.by.
### Используемый стек технологий:
1. Язык программирования - **Java**, версии 11
2. **Selenium** - библиотека для взаимодействия с веб-браузером
3. **TesnNG** - фреймворк для написания и выполнения автоматизированных тестов
4. **Maven** - инструмент для автоматизации сборки проектов
5. **Allure** - инструмент для формирования отчетов о тестировании
6. **Log4j** - библиотека для логирования
7. **Rest Assured** - библиотека дял реализации API тестов
8. **Lombok** - библиотека для сокращения шаблонного кода посредством аннотаций
9. **Jenkins** - программная система для обеспечения процесса непрерывной интеграции программного обеспечения. 
### Порядок действий для запуска проекта у себя на машине
1. Склонировать проект себе на машину
2. Запустить проект в своей среде разработки
3. Установить все зависимости из файла pom.xml
4. Запустить тесты с помощью следующей команды

### Основные команды для работы с проектом
Клонировать проект
```
git clone "reposiroty_url"
```
Собрать проект
```
mvn install
```
Запусить определенный набор тестов

Доступные наборы тестов: regression, userflow, apitest (чек-листы для них находятся по ссылку снизу)
```
mvn clean -Dsuite="набор тестов" -Dconfig=wildberries test
```
Достать отчет о результатах тестирования 
```
cd target/
allure serve
```
Удалить скомпилированные файлы из каталога taget
```
mvn clean
```

## Полезные ссылки 
Чек-листы для наборов тестов - (https://docs.google.com/spreadsheets/d/1Opw26fJkjDXuF2LO_o8r3zlDwh-aN34Vp1fk7KeGlpI/edit?usp=sharing)https://docs.google.com/spreadsheets/d/1Opw26fJkjDXuF2LO_o8r3zlDwh-aN34Vp1fk7KeGlpI/edit?usp=sharing).

Ссылка на презентацию проекта - (https://docs.google.com/presentation/d/1Tsey7NLMEXoXmzno5b3hhZ-aTnyPhiIK/edit?usp=sharing&ouid=102000424570315181245&rtpof=true&sd=true)

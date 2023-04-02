# Дипломный проект по профессии «Тестировщик»

Дипломный проект — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

## Процедура запуска автотестов:
Запуск контейнеров
Через Docker (скачать при необходимости) для MySQl, PostgerSQL и Node.js через терминал в IDE по команде: docker-compose up

## Запуск SUT:
по команде через терминал IDE:

* для БД MySQL: java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
* для БД Postgres: java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar

#### Приложение запускается на порту 8080:
http://localhost:8080/

## Запуск тестов:
По команде через терминал IDE:

* для БД MySQL: ./gradlew clean test -Durl="jdbc:mysql://localhost:3306/app"
* для БД Postgres: ./gradlew clean test -Durl="jdbc:mysql://localhost:5432/app"

Запуск репортинга (Allure):
По команде в терминале IDE: ./gradlew allureServe

## Завершение работы:
После окончания тестов по команде в термнале IDE: control+C (MacOS)

## Остановка контейнеров:
По команде в термнале IDE: docker-compose down

## Документация:
- [План 
автоматизации](https://github.com/DmitrySavostyanov/MyDiplomQA_Ver2/blob/FixError/docs/Plan.md)
- [Отчет по итогам 
тестирования](https://github.com/DmitrySavostyanov/MyDiplomQA_Ver2/blob/FixError/docs/Report.md)
- [Отчет по итогам 
автоматизации](https://github.com/DmitrySavostyanov/MyDiplomQA_Ver2/blob/FixError/docs/Summary.md)

## Демонстрация 

[Видео демонстрация запуска тестов](https://user-images.githubusercontent.com/110735452/229338586-310a4171-09cb-4350-9421-be827df0a1c0.mp4)




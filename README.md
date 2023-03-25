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

* для БД MySQL: ./gradlew clean test "-Durl=jdbc:mysql://localhost:3306/app"
* для БД Postgres: ./gradlew clean test "-Durl=jdbc:postgresql://localhost:5432/app"

* Запуск локально (одного) теста (пример для БД MySQL): ./gradlew clean test --tests CreditCardTest.shouldPayByAppDC "-Durl=jdbc:mysql://localhost:3306/app"


## Запуск репортинга (Allure):
По команде в терминале IDE: ./gradlew allureServe

## Завершение работы:
После окончания тестов по команде в термнале IDE: control+C (MAC)

## Остановка контейнеров:
По команде в термнале IDE: docker-compose down

## Документация:

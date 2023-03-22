# Отчет по итогам тестирования

## Краткое описание

Целью проведенной работы явилась автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.
На первом этапе было проведено ручное тестирование приложения с написанием тестового сценария для последующего написания автотестов. 
Второй этап - проведение автоматизированного тестирования, включающее в себя проверку позитивных и негативных сценариев покупки тура по дебетовой и кредитной карте:

 - **Тестирование проведено для двух БД (база данных)**: MySQL и PostgreSQL;

 - **Количество тест-кейсов**: 38 для двух БД - 4 позитивных, 34 негативных;

 - **Результат тестирования**:

**PostgreSQL:**
 - успешных - 16 (84,21%)
 - неуспешных - 6 (15,79%)
  
  ![postgress](https://photos.google.com/u/0/share/AF1QipMouMvTA-J_Z6I43afbyb7r1Gfb0jrvJAJKVdlRPs4NHEgpZzloPNDA4JhpT0BZgw/photo/AF1QipMYvNzpSow-LwDR43Fy7kTH7Ta1mMousaZpVdS7?key=di1jbWduazZCZnhjS2hBaTlmT1EyQ0JaSllQRWNB&hl=ru)
    ![postgress](https://photos.google.com/u/0/share/AF1QipMouMvTA-J_Z6I43afbyb7r1Gfb0jrvJAJKVdlRPs4NHEgpZzloPNDA4JhpT0BZgw/photo/AF1QipNNzCVklyAFtU5Srl-TiSvBGVE8LVtk6GxXRT_T?key=di1jbWduazZCZnhjS2hBaTlmT1EyQ0JaSllQRWNB&hl=ru)
  
  
  
**MySQL:**
 - успешных - 16 (84,21%)
 - неуспешных - 6 (15,79%)
  
   ![mySQL](https://photos.google.com/u/0/share/AF1QipMouMvTA-J_Z6I43afbyb7r1Gfb0jrvJAJKVdlRPs4NHEgpZzloPNDA4JhpT0BZgw/photo/AF1QipMr5xjDpkoQlWN-c32RCYiwoIMpGzSZo5sSvqEe?key=di1jbWduazZCZnhjS2hBaTlmT1EyQ0JaSllQRWNB&hl=ru)
    ![mySQL](https://photos.google.com/u/0/share/AF1QipMouMvTA-J_Z6I43afbyb7r1Gfb0jrvJAJKVdlRPs4NHEgpZzloPNDA4JhpT0BZgw/photo/AF1QipPbpXJl0XuOCKv-oAXJuZbc2X4MKaqKE4j1WPpc?key=di1jbWduazZCZnhjS2hBaTlmT1EyQ0JaSllQRWNB&hl=ru)
  
  

  
  
## Дефекты, обнаруженные во время тестирования

Оформлено шесть Issue. Необходимо принять в работу для исправления. 


## Примененное окружение:

 - Intellij IDEA (Community edition)
 - MacOS Monterey 12.5.1
 - Google Chrome Версия 111.0.5563.64
 - Java 11
 - GitHub
 - Docker Desktop
 - Selenide
 - Gradle
 - Junit 5
 - Lombok
 - Allure

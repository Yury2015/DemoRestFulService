# DemoRestFulService

Получить котировки quote:
http://localhost:8080/quote?isin=RU000A0JX0J2&bid=100.2&ask=101.9

Предоставление elvl по isin:
http://localhost:8080/elvl?isin=RU000A0JX0J2

Предоставление перечня всех elvls:
http://localhost:8080/elvlAll

# Maven:

Запуск: ./mvnw spring-boot:run

Создание jar файла: ./mvnw clean package

Запуск jar файла: java -jar target/gs-rest-service-0.1.0.jar


# Gradle:

Запуск: ./gradlew bootRun

Создание jar файла: ./gradlew build

Запуск jar файла: java -jar build/libs/gs-rest-service-0.1.0.jar
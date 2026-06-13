# UI and API Automated Tests Project

## Описание проекта
Проект содержит автоматизированные тесты UI и API для веб-приложения с использованием:
- Java 11
- Selenium WebDriver
- JUnit 4
- RestAssured
- Maven
- Allure Report

---

## Структура проекта

- `src/` — исходный код тестов
- `pom.xml` — зависимости и настройки Maven
- `README.md` — описание проекта
- `allure-results/` — результаты выполнения тестов (генерируются автоматически)

---

## Установка зависимостей

Перед запуском убедитесь, что установлены:
- Java 11
- Maven
- Google Chrome
- Allure CLI

---

## Запуск тестов

```bash
mvn clean test